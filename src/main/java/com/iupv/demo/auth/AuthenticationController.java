package com.iupv.demo.auth;

import com.iupv.demo.User.UserRepository;
import com.iupv.demo.util.PdfExtractData;
import com.iupv.demo.util.PdfHeadersDto;
import com.spire.pdf.PdfDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final PdfExtractData pdfExtractData;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping(value = "/pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PdfHeadersDto> extractPdfHeaders(@RequestPart(value = "file") MultipartFile file) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(file.getInputStream());
        System.out.println(pdfExtractData.extractHeaders(pdfDocument));
        return ResponseEntity.ok(pdfExtractData.extractHeaders(pdfDocument));
    }
}
