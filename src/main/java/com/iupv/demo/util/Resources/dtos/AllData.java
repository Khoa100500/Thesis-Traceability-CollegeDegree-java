package com.iupv.demo.util.Resources.dtos;

import com.iupv.demo.report.PdfHeadersDto;
import com.iupv.demo.score.StudentScoreDto;
import com.iupv.demo.signinfo.CertificateInfoDto;
import com.iupv.demo.signinfo.SignatureInfoDto;

import java.util.Set;

public record AllData(Set<StudentScoreDto> scores, PdfHeadersDto pdfHeadersDto,
                      SignatureInfoDto signatureInfoDtoList, CertificateInfoDto certificateInfoDto) {
}
