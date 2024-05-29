package com.iupv.demo.util.Resources.dtos;

public record CertificateInfo(boolean isVerifiedAgainstRoot, String issuer,
                              String subject,
                              String validFrom,
                              String validTo,
                              String validityOnSign,
                              String validityNow) {
}
