package com.project.elevage.intelligent.Smart_Farming.Services;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void envoyerAlerte(String message) {
        System.out.println("📢 ALERTE : " + message);

        // TODO : Envoyer un email, un SMS, ou un message Telegram
        // Exemple : envoyerEmail(message);
    }

    private void envoyerEmail(String message) {
        // Implémentation pour envoyer un email
    }
}
