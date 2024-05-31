package com.iupv.demo.util.component;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.signatures.*;
import com.iupv.demo.signinfo.CertificateInfoDto;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CertificateComponent {

    public static final String ROOT = "src/main/java/com/iupv/demo/util/Resources/root_X0F.crt";
    private static final PrintStream OUT_STREAM = System.out;
    private KeyStore ks;

    public CertificateComponent() {
        this.Initialize();
    }

    private void Initialize() {
        // Create your own root certificate store and add certificates
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException e) {
            System.out.println("KeyStore Exception: " + e.getMessage());
        }
        try {
            assert ks != null;
            ks.load(null, null);
        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
            System.out.println("Loading keystore failed: " + e.getMessage());
        }
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            System.out.println("Certificate Factory: " + e.getMessage());
        }
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(ROOT);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            assert cf != null;
            ks.setCertificateEntry("root", cf.generateCertificate(stream));
        } catch (KeyStoreException | CertificateException e) {
            System.out.println("Parsing certificate failed: " + e.getMessage());
        }
        this.setKeyStore(ks);
    }

    private com.iupv.demo.util.CertificateInfo showCertificateInfo(X509Certificate cert, Date signDate) throws CertificateEncodingException {
        String validityOnSign = "No info";
        String validityNow = "No info";

        X500Name x500name = new JcaX509CertificateHolder(cert).getIssuer();
        RDN email = x500name.getRDNs(BCStyle.EmailAddress)[0];
        String issuer = IETFUtils.valueToString(email.getFirst().getValue());

        X500Name x500name2 = new JcaX509CertificateHolder(cert).getSubject();
        RDN email1 = x500name2.getRDNs(BCStyle.EmailAddress)[0];
        String subject = IETFUtils.valueToString(email1.getFirst().getValue());

        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        date_format.setTimeZone(TimeZone.getTimeZone("Universal"));
        String validFrom = date_format.format(cert.getNotBefore());
        String validTo = date_format.format(cert.getNotAfter());

        // Check if a certificate was valid on the signing date
        try {
            cert.checkValidity(signDate);
            validityOnSign = "Valid";
        } catch (CertificateExpiredException e) {
            validityOnSign = "Expired";
        } catch (CertificateNotYetValidException e) {
            validityOnSign = "Wasn't valid yet";
        }

        // Check if a certificate is still valid now
        try {
            cert.checkValidity();
            validityNow = "Still valid";
        } catch (CertificateExpiredException e) {
            validityNow = "Expired";
        } catch (CertificateNotYetValidException e) {
            validityNow = "The certificate isn't valid yet";
        }

        return com.iupv.demo.util.CertificateInfo.builder().issuer(issuer).subject(subject).validFrom(validFrom).validTo(validTo)
                .validityOnSign(validityOnSign).validityNow(validityNow).build();
    }

    private CertificateInfoDto verifySignature(SignatureUtil signUtil, String name) {
        PdfPKCS7 pkcs7 = signUtil.readSignatureData(name);
        Certificate[] certs = pkcs7.getSignCertificateChain();
        boolean isVerifiedAgainstRoot = false;

        // Timestamp is a secure source of signature creation time,
        // because it's based on Time Stamping Authority service.
        Calendar cal = pkcs7.getTimeStampDate();

        // If there is no timestamp, use the current date
        if (TimestampConstants.UNDEFINED_TIMESTAMP_DATE == cal) {
            cal = Calendar.getInstance();
        }

        // Check if the certificate chain, presented in the PDF, can be verified against
        // the created key store.
        List<VerificationException> errors = null;
        try {
            errors = CertificateVerification.verifyCertificates(certs, ks, cal);
        } catch (CertificateEncodingException e) {
            System.out.println("Certificate encoding exception: " + e.getMessage());
        }
        assert errors != null;
        if (errors.isEmpty()) {
            isVerifiedAgainstRoot = true;
        }

        // Find out if certificates were valid on the signing date, and if they are still valid today
        X509Certificate cert = (X509Certificate) certs[0];
        com.iupv.demo.util.CertificateInfo c = null;
        try {
            c = showCertificateInfo(cert, cal.getTime());
        } catch (CertificateEncodingException e) {
            System.out.println(e.getMessage());
        }
        assert c != null;
        return new CertificateInfoDto(isVerifiedAgainstRoot, c.getIssuer(), c.getSubject(), c.getValidFrom(),
                c.getValidTo(), c.getValidityNow(), c.getValidityOnSign());
    }

    public CertificateInfoDto verifySignatures(PdfReader pdfReader) {
        PdfDocument pdfDocument = new PdfDocument(pdfReader);
        SignatureUtil signUtil = new SignatureUtil(pdfDocument);
        List<String> names = signUtil.getSignatureNames();

        return verifySignature(signUtil, names.getFirst());
    }

    private void setKeyStore(KeyStore ks) {
        this.ks = ks;
    }
}

