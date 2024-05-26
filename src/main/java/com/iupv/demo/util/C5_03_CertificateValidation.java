package com.iupv.demo.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.signatures.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class C5_03_CertificateValidation {
    public static final String ROOT = "src/main/java/com/iupv/demo/util/Resources/root_X0F.crt";

    public static final String EXAMPLE = "src/main/java/com/iupv/demo/util/Resources/KetQuaNhapDiem (18).pdf";

    private static final PrintStream OUT_STREAM = System.out;

    private KeyStore ks;

    public void verifySignatures(String path) throws IOException, GeneralSecurityException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(path));
        SignatureUtil signUtil = new SignatureUtil(pdfDoc);
        List<String> names = signUtil.getSignatureNames();

        OUT_STREAM.println(path);
        for (String name : names) {
            OUT_STREAM.println("===== " + name + " =====");
            verifySignature(signUtil, name);
        }
    }

    public void verifySignature(SignatureUtil signUtil, String name) throws GeneralSecurityException {
        PdfPKCS7 pkcs7 = getSignatureData(signUtil, name);
        Certificate[] certs = pkcs7.getSignCertificateChain();

        // Timestamp is a secure source of signature creation time,
        // because it's based on Time Stamping Authority service.
        Calendar cal = pkcs7.getTimeStampDate();

        // If there is no timestamp, use the current date
        if (TimestampConstants.UNDEFINED_TIMESTAMP_DATE == cal) {
            cal = Calendar.getInstance();
        }

        // Check if the certificate chain, presented in the PDF, can be verified against
        // the created key store.
        List<VerificationException> errors = CertificateVerification.verifyCertificates(certs, ks, cal);
        if (errors.isEmpty()) {
            OUT_STREAM.println("Certificates verified against the KeyStore");
        } else {
            OUT_STREAM.println(errors);
        }

        // Find out if certificates were valid on the signing date, and if they are still valid today
        for (int i = 0; i < certs.length; i++) {
            X509Certificate cert = (X509Certificate) certs[i];
            OUT_STREAM.println("=== Certificate " + i + " ===");
            showCertificateInfo(cert, cal.getTime());
        }

        // Take the signing certificate
        X509Certificate signCert = (X509Certificate) certs[0];

        // Take the certificate of the issuer of that certificate (or null if it was self-signed).
        X509Certificate issuerCert = (certs.length > 1 ? (X509Certificate) certs[1] : null);
    }

    public PdfPKCS7 getSignatureData(SignatureUtil signUtil, String name) throws GeneralSecurityException {
        PdfPKCS7 pkcs7 = signUtil.readSignatureData(name);

        OUT_STREAM.println("Signature covers whole document: " + signUtil.signatureCoversWholeDocument(name));
        OUT_STREAM.println("Document revision: " + signUtil.getRevision(name) + " of " + signUtil.getTotalRevisions());
        OUT_STREAM.println("Integrity check OK? " + pkcs7.verifySignatureIntegrityAndAuthenticity());

        return pkcs7;
    }

    public void showCertificateInfo(X509Certificate cert, Date signDate) {
        OUT_STREAM.println("Issuer: " + cert.getIssuerX500Principal());
        OUT_STREAM.println("Subject: " + cert.getSubjectX500Principal());
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        date_format.setTimeZone(TimeZone.getTimeZone("Universal"));
        OUT_STREAM.println("Valid from: " + date_format.format(cert.getNotBefore()));
        OUT_STREAM.println("Valid to: " + date_format.format(cert.getNotAfter()));

        // Check if a certificate was valid on the signing date
        try {
            cert.checkValidity(signDate);
            OUT_STREAM.println("The certificate was valid at the time of signing.");
        } catch (CertificateExpiredException e) {
            OUT_STREAM.println("The certificate was expired at the time of signing.");
        } catch (CertificateNotYetValidException e) {
            OUT_STREAM.println("The certificate wasn't valid yet at the time of signing.");
        }

        // Check if a certificate is still valid now
        try {
            cert.checkValidity();
            OUT_STREAM.println("The certificate is still valid.");
        } catch (CertificateExpiredException e) {
            OUT_STREAM.println("The certificate has expired.");
        } catch (CertificateNotYetValidException e) {
            OUT_STREAM.println("The certificate isn't valid yet.");
        }
    }


    public static void main(String[] args) throws IOException, GeneralSecurityException {

        C5_03_CertificateValidation app = new C5_03_CertificateValidation();

        // Create your own root certificate store and add certificates
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null, null);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        try (FileInputStream stream = new FileInputStream(ROOT)) {
            ks.setCertificateEntry("root", cf.generateCertificate(stream));
        }

        app.setKeyStore(ks);
        app.verifySignatures(EXAMPLE);

    }
    private void setKeyStore(KeyStore ks) {
        this.ks = ks;
    }
}