package com.iupv.demo.email;

import com.iupv.demo.report.ReportDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public void SendEmail(String to, MultipartFile file, ReportDto report) throws MessagingException {
        Logger logger = LoggerFactory.getLogger(EmailSenderService.class);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        Context context = new Context();
        context.setVariable("recipientName", report.lecturerName());
        context.setVariable("courseName", report.courseName());
        context.setVariable("groupId", report.groupId());
        context.setVariable("timePosted", report.timePosted());

        String text = templateEngine.process("emailtemplate", context);

        mimeMessageHelper.setPriority(1);
        mimeMessageHelper.setFrom("IU-EduTrace");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject("Student Scores Report Upload Successful");
        mimeMessageHelper.setText(text, true);
        mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);

        mailSender.send(mimeMessage);
        logger.info("Student Scores Report Upload Successful");
    }
}
