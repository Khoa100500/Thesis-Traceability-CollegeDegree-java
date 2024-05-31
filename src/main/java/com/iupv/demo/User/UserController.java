package com.iupv.demo.User;

import com.iupv.demo.util.PdfDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller("/api/v1/user")
public class UserController {

    private final PdfDataService pdfDataService;

    @PostMapping(value = "/{id}/report", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> postReport(@PathVariable Integer id, @RequestPart(value = "file") MultipartFile file) {
        return ResponseEntity.ok(pdfDataService.uploadReport(id, file));
    }
}
