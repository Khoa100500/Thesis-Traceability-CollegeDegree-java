package com.iupv.demo.User;

import com.iupv.demo.report.ReportRepository;
import com.iupv.demo.report.ReportService;
import com.iupv.demo.report.SimpleReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Controller()
@RequestMapping("/api/v1/user")
public class UserController {

    private final ReportRepository reportRepository;
    private final ReportService reportService;

    @PostMapping(value = "/report", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> createReport(@CurrentSecurityContext(expression = "authentication.name") String username,
                                                @RequestPart(value = "file") MultipartFile file) {

        return ResponseEntity.ok(reportService.createReportByUsername(username, file));
    }

    @GetMapping("/reports/count")
    public ResponseEntity<Long> getReportsCount(@CurrentSecurityContext(expression = "authentication.name") String username) {
        return ResponseEntity.ok(reportService.getReportCountByUsername(username));
    }

    @GetMapping("/simple-reports")
    public ResponseEntity<List<SimpleReportDto>> getReportList(@CurrentSecurityContext(expression = "authentication.name") String username) {
        return ResponseEntity.ok(reportService.getSimpleReportByUsername(username));
    }
}
