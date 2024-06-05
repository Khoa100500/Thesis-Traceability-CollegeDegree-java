package com.iupv.demo;

import com.iupv.demo.report.ReportDto;
import com.iupv.demo.report.ReportService;
import com.iupv.demo.report.SimpleReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/guest")
public class GuestController {

    private final ReportService reportService;

    @GetMapping("/simple-reports/{id}")
    public ResponseEntity<List<SimpleReportDto>> getReportList(@PathVariable  String id) {
        return ResponseEntity.ok(reportService.getSimpleReportByStudentId(id));
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<ReportDto> getReport(@PathVariable Integer id) {
        return ResponseEntity.ok(reportService.getReportByID(id));
    }
}
