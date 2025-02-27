package com.project.elevage.intelligent.Smart_Farming.Repositories;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class LogRepository {
    /**
     * Simule la récupération des logs récents.
     */
    public List<String> recupererLogsRecents() {
        return Arrays.asList(
                "2024-02-22 10:00:01 - Erreur MQTT : Connexion interrompue",
                "2024-02-22 10:05:34 - Dispositif X a envoyé une alerte",
                "2024-02-22 10:10:12 - Requête HTTP non autorisée détectée"
        );
    }
}
