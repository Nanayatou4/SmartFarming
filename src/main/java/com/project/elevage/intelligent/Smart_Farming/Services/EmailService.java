package com.project.elevage.intelligent.Smart_Farming.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());

    public void sendAlert(String to, String subject, String message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            mailSender.send(mailMessage);
            LOGGER.info("Email envoyé avec succès à : " + to);
        } catch (MailException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'envoi de l'email", e);
        }
    }
}
