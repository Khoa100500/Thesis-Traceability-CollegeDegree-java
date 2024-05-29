package com.iupv.demo.util.Resources.dtos;

import java.util.List;

public record AllData(List<StudentScores> scores, PdfHeaders pdfHeaders,
                      SignatureInfo signatureInfoList, CertificateInfo certificateInfo) {
}
