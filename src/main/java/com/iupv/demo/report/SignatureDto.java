package com.iupv.demo.report;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.iupv.demo.signinfo.Signature}
 */
public record SignatureDto(Boolean isVerifiedAgainstRoot, String issuer, String subject, String validFrom,
                           String validTo, String validityNow, String validityOnSign, String contactInfo,
                           String digestAlgorithm, String encryptionAlgorithm, Byte fieldOnPage, String filterSubtype,
                           Boolean integrity, Boolean isAnnotationsAllowed, Boolean isFillInAllowed,
                           Boolean isSignatureInvisible, Boolean isTimeStampVerified, String location, String reason,
                           String revision, Boolean signatureCoversWholeDocument, String signatureType,
                           LocalDate signedOn, String signerAlternativeName, String signerName, String timeStamp,
                           String timeStampService) implements Serializable {
}