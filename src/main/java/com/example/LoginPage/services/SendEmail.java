package com.example.LoginPage.services;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {

    private final JavaMailSender mailSender;

    public SendEmail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body) throws Exception {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("larissacravoccamara@gmail.com");

            mailSender.send(message);
        } catch (MailException e) {
            throw new Exception("Falha ao enviar e-mail: " + e.getMessage());
        }
    }
}