package com.inventory.rootPackage.mailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    // ✅ Get "spring.mail.username" from application.properties
    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * Send mail without attachment (basic)
     */
    public void sendEmail(String to, String subject, String body) throws MessagingException {
        sendEmailWithAttachment(to, subject, body, null);
    }

    /**
     * Send mail with optional PDF attachment
     */
    public void sendEmailWithAttachment(String to, String subject, String body, File attachment) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // true = multipart message (needed for attachment)
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(fromEmail);  // ✅ never null now
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);

        // add attachment only if provided
        if (attachment != null && attachment.exists()) {
            FileSystemResource file = new FileSystemResource(attachment);
            helper.addAttachment(file.getFilename(), file);
        }

        mailSender.send(mimeMessage);
    }
}
