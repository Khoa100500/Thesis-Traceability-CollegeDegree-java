package com.iupv.demo.report;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/v1/report")
public class ReportController {

    private final ReportService reportService;


    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReport(@PathVariable Integer id) {
        return ResponseEntity.ok(reportService.getReportByID(id));
    }
}
