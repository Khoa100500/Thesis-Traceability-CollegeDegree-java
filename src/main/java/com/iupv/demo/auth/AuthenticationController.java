package com.iupv.demo.auth;

import com.iupv.demo.report.Report;
import com.iupv.demo.report.ReportDto;
import com.iupv.demo.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final PdfDataService pdfDataService;


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
    public ResponseEntity<Integer> postReport(@RequestPart(value = "file") MultipartFile file) {
        return ResponseEntity.ok(pdfDataService.uploadReport(1, file));
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<ReportDto> getReport(@PathVariable Integer id) {
        return ResponseEntity.ok(pdfDataService.findReportById(id));
    }
}
