package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantActivity;
import com.project.elevage.intelligent.Smart_Farming.Model.ServerMetrics;
import com.project.elevage.intelligent.Smart_Farming.Repositories.AdminSystemEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.LogRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class MonitoringService {

    @Autowired
    private TenantActivityRepository tenantActivityRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdminSystemEntityRepository adminSystemRepository;

    /**
     * Récupère l'activité des locataires et dispositifs.
     */
    public List<TenantActivity> obtenirActiviteGlobale() {
        return tenantActivityRepository.findAll();
    }

    /**
     * Récupère les performances du serveur (CPU, RAM, etc.).
     */
    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);

    public ServerMetrics obtenirPerformancesServeur() {
        // Cast pour obtenir les bonnes méthodes
        com.sun.management.OperatingSystemMXBean osBean =
                (com.sun.management.OperatingSystemMXBean) ManagementFactory.getPlatformMXBean(com.sun.management.OperatingSystemMXBean.class);

        // Récupérer la charge CPU
        double chargeCpu = osBean.getSystemCpuLoad() * 100;

        // Récupérer la mémoire
        long memTotal = Runtime.getRuntime().totalMemory();
        long memLibre = Runtime.getRuntime().freeMemory();

        // Alerte si la charge CPU est élevée
        if (chargeCpu > 80) {
            logger.warn("Charge CPU élevée: {}%", chargeCpu);

            // Récupérer l'email de l'admin système
            String adminEmail = adminSystemRepository.findAdminEmailById(1L);

            if (adminEmail != null) {
                emailService.sendAlert(adminEmail, "Alerte CPU élevée", "Charge CPU: " + chargeCpu + "%");
            } else {
                logger.error("Impossible d'envoyer l'alerte: aucun email admin trouvé.");
            }        }

        return new ServerMetrics(chargeCpu, memTotal, memLibre);
    }
    /**
     * Récupère et analyse les logs du système.
     */
    public List<String> analyserLogs() {
        return logRepository.recupererLogsRecents();
    }

    /**
     * Simule la résolution d'un incident basé sur les logs.
     */
    public String resoudreIncident(String logId) {
        return "Incident lié au log " + logId + " a été résolu.";
    }
}
