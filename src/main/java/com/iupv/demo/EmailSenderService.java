package com.iupv.demo;

import com.iupv.demo.report.ReportDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
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
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        Context context = new Context();
        context.setVariable("recipientName", report.lecturerName());
        context.setVariable("courseName", report.courseName());
        context.setVariable("groupId", report.groupId());
        context.setVariable("timePosted", report.timePosted());

        String text = templateEngine.process("emailtemplate", context);

        mimeMessageHelper.setPriority(1);
        mimeMessageHelper.setFrom("pdangkhoa445@gmail.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject("Student Scores Report Upload Successful");
        mimeMessageHelper.setText(text, true);
        mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);

        mailSender.send(mimeMessage);

    }
}
