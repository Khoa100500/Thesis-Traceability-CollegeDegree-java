package com.iupv.demo.util;

import com.itextpdf.kernel.pdf.PdfReader;
import com.iupv.demo.User.User;
import com.iupv.demo.User.UserRepository;
import com.iupv.demo.exception.InvalidSignatureException;
import com.iupv.demo.report.*;
import com.iupv.demo.score.StudentScore;
import com.iupv.demo.score.StudentScoreMapper;
import com.iupv.demo.score.StudentScoreRepository;
import com.iupv.demo.signinfo.*;
import com.iupv.demo.util.Resources.dtos.AllData;
import com.iupv.demo.util.component.CertificateComponent;
import com.iupv.demo.util.component.DataComponent;
import com.iupv.demo.util.component.SignatureInfoComponent;
import com.spire.pdf.PdfDocument;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class PdfDataService {

    private final DataComponent p;
    private final SignatureInfoComponent s;
    private final CertificateComponent c;
    private final UserRepository userRepository;
    private final SignatureRepository signatureRepository;
    private final ReportRepository reportRepository;
    private final StudentScoreMapper studentScoreMapper;
    private final PdfHeaderMapper pdfHeaderMapper;
    private final CertificateMapper certificateMapper;
    private final SignatureMapper signatureMapper;
    private final StudentScoreRepository studentScoreRepository;

    private Boolean checkExistSignature(Signature signature) {
        return signatureRepository.existsBySubjectAndSignedOn(signature.getSubject(), signature.getSignedOn());
    }

    private AllData getAllPdfData(MultipartFile file) {
        PdfReader signatureReader;
        PdfReader certificateReader;
        AllData allData = null;
        try {
            PdfDocument pdfDocument = new PdfDocument(file.getBytes());
            signatureReader = new PdfReader(file.getInputStream());
            certificateReader = new PdfReader(file.getInputStream());
            allData = new AllData(p.extractStudentScores(pdfDocument), p.extractHeaders(pdfDocument),
                    s.inspectSignatures(signatureReader), c.verifySignatures(certificateReader));
        } catch (IOException e) {
            System.out.println("Cannot read pdf file: " + e.getMessage());
        }
        return allData;
    }

    private boolean checkData(CertificateInfoDto certificateInfoDto, PdfHeadersDto pdfHeadersDto) {
        boolean result = true;
        boolean isVerifiedAgainstRoot = certificateInfoDto.isVerifiedAgainstRoot();
        String signerEmail = certificateInfoDto.subject();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User not found at pdf validation"));
        String userEmail = user.getEmail();
        String userFullname = user.getUserFullname();
        String lecturerName = pdfHeadersDto.lecturerName();
        if(!userEmail.equals(signerEmail)) {
            throw new InvalidSignatureException("Invalid email");
        }
        if(!isVerifiedAgainstRoot) {
            throw new InvalidSignatureException("Cannot verify against root");
        }
        if(!userFullname.equals(lecturerName)) {
            throw new InvalidSignatureException("User and lecturer name does not match");
        }
        return result;
    }


    private Integer PostReport(String username, AllData allData) {
        Signature signature = new Signature();
        //Set StudentScores
        Set<StudentScore> studentScores = studentScoreMapper.toEntity(allData.scores());

        // Set signature
        signatureMapper.partialUpdate(allData.signatureInfoDtoList(), signature);
        certificateMapper.partialUpdate(allData.certificateInfoDto(), signature);

        //Set Report
        Report report = pdfHeaderMapper.toEntity(allData.pdfHeadersDto());

        report.setStudentScores(studentScores);
        for(StudentScore studentScore : studentScores) {
            studentScore.setReport(report);
        }
        if(!checkExistSignature(signature)) {
            signature.addReport(report);
        } else {
            signature = signatureRepository.findBySubjectAndSignedOn(signature.getSubject(), signature.getSignedOn());
        }
        report.setSign(signature);
        report.setTimePosted(Instant.now());
        report.setUser(userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User not found")));

        signatureRepository.save(signature);
        reportRepository.save(report);
        studentScoreRepository.saveAll(studentScores);

        return report.getId();
    }

    public Integer uploadReport(String username, MultipartFile file) {
        AllData allData = getAllPdfData(file);
        if (checkData(allData.certificateInfoDto(), allData.pdfHeadersDto())) {
            return PostReport(username, allData);
        } else {
            throw new InvalidSignatureException("Invalid pdf information");
        }
    }
}