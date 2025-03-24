package com.project.elevage.intelligent.Smart_Farming.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Value("${spring.mail.username}")  // Récupère l'email configuré dans application.properties
    private String fromEmail;

    public void sendAlert(String to, String subject, String message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            mailSender.send(mailMessage);
            LOGGER.info("Email envoyé avec succès à {}", to);
        } catch (MailException e) {
            LOGGER.error("Erreur lors de l'envoi de l'email à {} : {}", to, e.getMessage());
        }
    }
}
