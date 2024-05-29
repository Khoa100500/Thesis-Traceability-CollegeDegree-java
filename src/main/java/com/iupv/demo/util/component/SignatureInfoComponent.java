package com.iupv.demo.util.component;

import com.itextpdf.bouncycastle.asn1.tsp.TSTInfoBC;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.annot.PdfWidgetAnnotation;
import com.itextpdf.signatures.CertificateInfo;
import com.itextpdf.signatures.PdfPKCS7;
import com.itextpdf.signatures.SignaturePermissions;
import com.itextpdf.signatures.SignatureUtil;
import com.itextpdf.signatures.TimestampConstants;

import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import com.iupv.demo.util.Resources.dtos.SignatureInfo;
import lombok.AllArgsConstructor;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.springframework.stereotype.Component;

@Component
public class SignatureInfoComponent {

    private SignatureInfo inspectSignature(PdfDocument pdfDoc, SignatureUtil signUtil, PdfAcroForm form,
                                           String name) {
        List<PdfWidgetAnnotation> widgets = form.getField(name).getWidgets();
        boolean isSignatureInvisible = false;
        int fieldOnPage = 0;
        String signerAlternativeName = "No signer alternative name";
        String timeStamp = "No TimeStamp";
        String timeStampService = "No info";
        boolean isTimeStampVerified = false;
        String contactInfo = "No info";
        String location = "No info";
        String reason = "No info";
        boolean integrity = false;

        // Check the visibility of the signature annotation
        if (widgets != null && !widgets.isEmpty()) {
            Rectangle pos = widgets.getFirst().getRectangle().toRectangle();
            int pageNum = pdfDoc.getPageNumber(widgets.getFirst().getPage());
            if (pos.getWidth() == 0 || pos.getHeight() == 0) {
                isSignatureInvisible = true;
            } else {
                fieldOnPage = pageNum;
            }
        }

        /* Find out how the message digest of the PDF bytes was created,
         * how these bytes and additional attributes were signed
         * and how the signed bytes are stored in the PDF
         */
        PdfPKCS7 pkcs7 = signUtil.readSignatureData(name);

        boolean signatureCoversWholeDocument = signUtil.signatureCoversWholeDocument(name);
        String revision = signUtil.getRevision(name) + " of " + signUtil.getTotalRevisions();

        try {
            integrity = pkcs7.verifySignatureIntegrityAndAuthenticity();
        } catch (GeneralSecurityException e) {
            System.out.println("Error: " + e.getMessage());
        }
        String digestAlgorithm = pkcs7.getDigestAlgorithmName();
        String encryptionAlgorithm = pkcs7.getSignatureAlgorithmName();
        String filterSubtype = pkcs7.getFilterSubtype().toString();

        // Get the signing certificate to find out the name of the signer.
        X509Certificate cert = pkcs7.getSigningCertificate();
        String signerName = CertificateInfo.getSubjectFields(cert).getField("CN");
        if (pkcs7.getSignName() != null) {
            signerAlternativeName = pkcs7.getSignName();
        }

        // Get the signing time
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");

        /* Mind that the getSignDate() method is not that secure as timestamp
         * because it's based only on signature author claim. I.e. this value can only be trusted
         * if signature is trusted, and it cannot be used for signature verification.
         */
        LocalDate signedOn = LocalDate.parse(date_format.format(pkcs7.getSignDate().getTime()));

        /* If a timestamp was applied, retrieve information about it.
         * Timestamp is a secure source of signature creation time,
         * because it's based on Time Stamping Authority service.
         */
        if (TimestampConstants.UNDEFINED_TIMESTAMP_DATE != pkcs7.getTimeStampDate()) {
            timeStamp = date_format.format(pkcs7.getTimeStampDate().getTime());
            TSTInfo ts = ((TSTInfoBC) pkcs7.getTimeStampTokenInfo()).getTstInfo();
            timeStampService = ts.getTsa().toString();
            try {
                isTimeStampVerified = pkcs7.verifyTimestampImprint();
            } catch (GeneralSecurityException e) {
                System.out.println(e.getMessage());
            }
        }

        if(pkcs7.getLocation() != null) {
            location = pkcs7.getLocation();
        }
        if(pkcs7.getReason() != null) {
            reason = pkcs7.getReason();
        }

        /* If you want less common entries than PdfPKCS7 object has, such as the contact info,
         * you should use the signature dictionary and get the properties by name.
         */
        PdfDictionary sigDict = signUtil.getSignatureDictionary(name);
        PdfString contact = sigDict.getAsString(PdfName.ContactInfo);
        if (contact != null) {
            contactInfo = contact.toString();
        }

        /* Every new signature can add more restrictions to a document, but it can't take away previous restrictions.
         * So if you want to retrieve information about signatures restrictions, you need to pass
         * the SignaturePermissions instance of the previous signature, or null if there was none.
         */
        SignaturePermissions perms = new SignaturePermissions(sigDict, null);
        String signatureType = (perms.isCertification() ? "certification" : "approval");
        boolean isFillInAllowed = perms.isFillInAllowed();
        boolean isAnnotationsAllowed = perms.isAnnotationsAllowed();

        return new SignatureInfo(isSignatureInvisible, fieldOnPage, digestAlgorithm, encryptionAlgorithm, filterSubtype,
                signerName, signerAlternativeName, signedOn,timeStamp, timeStampService, isTimeStampVerified, location,
                reason, contactInfo, signatureType, isFillInAllowed, isAnnotationsAllowed, integrity, signatureCoversWholeDocument, revision);
    }

    public SignatureInfo inspectSignatures(PdfReader pdfReader) {
        PdfDocument pdfDoc = new PdfDocument(pdfReader);
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, false);
        SignatureUtil signUtil = new SignatureUtil(pdfDoc);
        List<String> names = signUtil.getSignatureNames();

        return inspectSignature(pdfDoc, signUtil, form, names.getFirst());
    }
}

@AllArgsConstructor
class Result {
    SignaturePermissions signaturePermissions;
    SignatureInfo signatureInfo;
}