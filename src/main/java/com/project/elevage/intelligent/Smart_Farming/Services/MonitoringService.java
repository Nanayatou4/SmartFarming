package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantActivity;
import com.project.elevage.intelligent.Smart_Farming.Model.ServerMetrics;
import com.project.elevage.intelligent.Smart_Farming.Repositories.LogRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.List;

@Service
public class MonitoringService {

    @Autowired
    private TenantActivityRepository tenantActivityRepository;

    @Autowired
    private LogRepository logRepository;

    /**
     * Récupère l'activité des locataires et dispositifs.
     */
    public List<TenantActivity> obtenirActiviteGlobale() {
        return tenantActivityRepository.findAll();
    }

    /**
     * Récupère les performances du serveur (CPU, RAM, etc.).
     */
    public ServerMetrics obtenirPerformancesServeur() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        double chargeCpu = osBean.getSystemLoadAverage();
        long memTotal = Runtime.getRuntime().totalMemory();
        long memLibre = Runtime.getRuntime().freeMemory();

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
