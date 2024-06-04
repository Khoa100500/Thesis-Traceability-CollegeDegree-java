package com.iupv.demo.report;

import com.iupv.demo.User.UserController;
import com.iupv.demo.util.PdfDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;
    private final SimpleReportMapper simpleReportMapper;
    private final ReportMapper reportMapper;
    private final PdfDataService pdfDataService;

    public List<SimpleReportDto> getSimpleReportByUsername(String username) {
        return simpleReportMapper.toDto(reportRepository.findByUser_Username(username));
    }

    public Long getReportCountByUsername(String username) {
        return reportRepository.countByUser_Username(username);
    }

    public ReportDto getReportByID(Integer reportID) {
        return reportMapper.toDto(reportRepository.findById(reportID).orElseThrow(
                () -> new NoSuchElementException("Report id not found")));
    }

    public Integer createReportByUsername(String username, MultipartFile file) {
        return pdfDataService.uploadReport(username, file);
    }
}
