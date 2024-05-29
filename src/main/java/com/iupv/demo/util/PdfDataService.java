package com.iupv.demo.util;

import com.itextpdf.kernel.pdf.PdfReader;
import com.iupv.demo.User.User;
import com.iupv.demo.User.UserRepository;
import com.iupv.demo.util.Resources.dtos.AllData;
import com.iupv.demo.util.component.CertificateComponent;
import com.iupv.demo.util.component.DataComponent;
import com.iupv.demo.util.component.SignatureInfoComponent;
import com.spire.pdf.PdfDocument;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class PdfDataService {

    private final DataComponent p;
    private final SignatureInfoComponent s;
    private final CertificateComponent c;
    private final UserRepository userRepository;

    public AllData getAllPdfData(MultipartFile file) {
        PdfReader signatureReader;
        PdfReader certificateReader;
        AllData allData = null;
        try {
            PdfDocument pdfDocument = new PdfDocument(file.getInputStream());
            signatureReader = new PdfReader(file.getInputStream());
            certificateReader = new PdfReader(file.getInputStream());
            allData = new AllData(p.extractStudentScores(pdfDocument), p.extractHeaders(pdfDocument),
                    s.inspectSignatures(signatureReader), c.verifySignatures(certificateReader));
        } catch (IOException e) {
            System.out.println("Cannot read pdf file: " + e.getMessage());
        }
        return allData;
    }

    public boolean checkData(AllData allData) {
        boolean isVerifiedAgainstRoot = allData.certificateInfo().isVerifiedAgainstRoot();
        String signerEmail = allData.certificateInfo().subject();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        String userEmail = user.getEmail();
        String userFullname = user.getUserFullname();
        String signerFullname = allData.pdfHeaders().lecturerName();
        return (userEmail.equals(signerEmail)) && isVerifiedAgainstRoot && (userFullname.equals(signerFullname));

    }

//    public void PostReport(MultipartFile file) {
//        AllData allData = getAllPdfData(file);
//
//    }
}