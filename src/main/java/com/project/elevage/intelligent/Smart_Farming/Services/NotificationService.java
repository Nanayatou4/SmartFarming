package com.project.elevage.intelligent.Smart_Farming.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private EmailService emailService;

    public void envoyerAlerte(String destinataire, String message) {
        System.out.println("ALERTE : " + message);

        // Envoi de l'alerte par email
        emailService.sendAlert(destinataire, "Alerte Système", message);


    }
}
