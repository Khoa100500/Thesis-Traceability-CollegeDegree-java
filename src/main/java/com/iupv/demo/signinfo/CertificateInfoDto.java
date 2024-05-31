package com.iupv.demo.signinfo;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Signature}
 */

public record CertificateInfoDto(Boolean isVerifiedAgainstRoot, String issuer, String subject, String validFrom,
                                 String validTo, String validityNow, String validityOnSign) implements Serializable {
}