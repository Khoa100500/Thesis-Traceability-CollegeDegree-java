package com.iupv.demo.util.Resources.dtos;

public record SignatureInfo(boolean isSignatureInvisible, int fieldOnPage, String digestAlgorithm,
                            String encryptionAlgorithm,
                            String filterSubtype, String signerName, String signerAlternativeName, java.time.LocalDate signedOn, String timeStamp,
                            String timeStampService, boolean isTimeStampVerified, String location, String reason,
                            String contactInfo, String signatureType, boolean isFillInAllowed,
                            boolean isAnnotationsAllowed, boolean integrity, boolean signatureCoversWholeDocument, String revision) {
}
