package com.soft.mailinglist.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendRequestTOEmail(String email, String text) {
        log.info("Attempting to send email to address: {}", email);

        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("text", text);

        try {
            String htmlContent = templateEngine.process("emailNewsletter", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Request");
            mimeMessageHelper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
            log.info("Email successfully sent to address: {}", email);
        } catch (MessagingException e) {
            log.warn("Failed to send email to address: {}", email, e);
            e.printStackTrace();
        }
    }

}