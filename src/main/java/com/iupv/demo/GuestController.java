package com.iupv.demo;

import com.iupv.demo.report.*;
import com.iupv.demo.score.StudentScore;
import com.iupv.demo.score.StudentScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/guest")
public class GuestController {

    private final ReportService reportService;
    private final ReportRepository reportRepository;
    private final StudentScoreRepository studentScoreRepository;

    @GetMapping("/simple-reports/{id}")
    public ResponseEntity<List<SimpleReportDto>> getReportList(@PathVariable  String id) {
        return ResponseEntity.ok(reportService.getSimpleReportByStudentId(id));
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<ReportDto> getReport(@PathVariable Integer id) {
        return ResponseEntity.ok(reportService.getReportByID(id));
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<List<StudentRecordDto>> getPdfHeadersList(@PathVariable String id) {
        return ResponseEntity.ok(reportService.getReportByStudentID(id));
    }
}
