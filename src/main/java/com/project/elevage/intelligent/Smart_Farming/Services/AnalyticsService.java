package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.SystemStatus.SystemStatusEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.SystemStatusEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantAdminEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    @Autowired
    private SystemStatusEntityRepository systemStatusRepository;

    @Autowired
    private TenantAdminEntityRepository  tenantAdminRepository;


    // Moyenne de température pour tous les dispositifs (Admin Système)
    public Map<String, Double> getGlobalAverageTemperature() {
        List<SystemStatusEntity> statuses = systemStatusRepository.findAll();
        return statuses.stream()
                .collect(Collectors.groupingBy(
                        SystemStatusEntity::getDeviceName,
                        Collectors.averagingDouble(SystemStatusEntity::getTemperature)
                ));
    }

    // Moyenne d’humidité pour tous les dispositifs (Admin Système)
    public Map<String, Double> getGlobalAverageHumidity() {
        List<SystemStatusEntity> statuses = systemStatusRepository.findAll();
        return statuses.stream()
                .collect(Collectors.groupingBy(
                        SystemStatusEntity::getDeviceName,
                        Collectors.averagingDouble(SystemStatusEntity::getHumidity)
                ));
    }

    // Vérifier si le TenantAdmin appartient bien au tenant
    private void verifierTenantAdmin(String email, Long tenantId) {
        TenantAdminEntity tenantAdmin = tenantAdminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("TenantAdmin non trouvé"));

        if (!tenantAdmin.getTenant().getId().equals(tenantId)) {
            throw new RuntimeException("Accès refusé : ce TenantAdmin n'appartient pas à ce tenant !");
        }
    }

    // Moyenne de température pour un Tenant spécifique
    public Map<String, Double> getTenantAverageTemperature(String email, Long tenantId) {
        verifierTenantAdmin(email, tenantId); // Vérification

        List<SystemStatusEntity> status = systemStatusRepository.findByTenantId(tenantId);
        return status.stream()
                .collect(Collectors.groupingBy(
                        SystemStatusEntity::getDeviceName,
                        Collectors.averagingDouble(SystemStatusEntity::getTemperature)
                ));
    }


    // Moyenne d’humidité pour un Tenant spécifique
    public Map<String, Double> getTenantAverageHumidity(String email,Long tenantId) {
        verifierTenantAdmin(email, tenantId); // Vérification

        List<SystemStatusEntity> status = systemStatusRepository.findByTenantId(tenantId);
        return status.stream()
                .collect(Collectors.groupingBy(
                        SystemStatusEntity::getDeviceName,
                        Collectors.averagingDouble(SystemStatusEntity::getHumidity)
                ));
    }
}
