package com.project.elevage.intelligent.Smart_Farming.Services;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class MaintenanceService {

    private static final Logger logger = Logger.getLogger(MaintenanceService.class.getName());

    public String updateSystem() {
        logger.info("Mise à jour du système en cours...");
        return "Mise à jour effectuée avec succès !";
    }

    public String restartService(String serviceName) {
        logger.info("Redémarrage du service : " + serviceName);
        return "Service " + serviceName + " redémarré avec succès.";
    }
}
