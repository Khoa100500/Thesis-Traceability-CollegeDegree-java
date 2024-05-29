package com.iupv.demo.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class CertificateInfo {
    String issuer;
    String subject;
    String validFrom;
    String validTo;
    String validityOnSign;
    String validityNow;
}
