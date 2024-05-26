package com.iupv.demo.util;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PdfExtractDataService {

    private final PdfExtractData pdfExtractData;

    public PdfHeadersDto extractPdfHeaders() {
        return null;
    }

}
