package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Telemetrie.TelemetryDataEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TelemetryDataEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantAdminEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TelemetryDataService {

    @Autowired
    private TelemetryDataEntityRepository telemetryDataRepository;

    @Autowired
    private TenantAdminEntityRepository  tenantAdminRepository;

    ///  **************************ADMIN SYSTEM******************************
    /**
     * Enregistrer une nouvelle donnée de télémétrie
     */
    public TelemetryDataEntity saveTelemetryData(TelemetryDataEntity data) {
        data.setTimestamp(LocalDateTime.now()); // Date actuelle
        return telemetryDataRepository.save(data);
    }

    /**
     * Récupérer toutes les données de télémétrie
     */
    public List<TelemetryDataEntity> getAllTelemetryData() {
        return telemetryDataRepository.findAll();
    }

    /**
     * Récupérer les données d’un appareil spécifique
     */
    public List<TelemetryDataEntity> getTelemetryDataByDevice(Long deviceId) {
        return telemetryDataRepository.findByDeviceId(deviceId);
    }

    /// ********************************TENANT ADMIN**********************************

    /**
     * Vérifier si le Tenant Admin appartient bien au tenant
     */
    private void verifierTenantAdmin(String email, Long tenantId) {
        TenantAdminEntity tenantAdmin = tenantAdminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("TenantAdmin non trouvé"));

        if (!tenantAdmin.getTenant().getId().equals(tenantId)) {
            throw new RuntimeException("Accès refusé : ce TenantAdmin n'appartient pas à ce tenant !");
        }
    }

    /**
     * Récupérer les données de télémétrie pour un tenant spécifique (Tenant Admin)
     */
    public List<TelemetryDataEntity> getTelemetryDataByTenant(String email, Long tenantId) {
        verifierTenantAdmin(email, tenantId); // Vérification d'accès
        return telemetryDataRepository.findByTenantId(tenantId);
    }

    /**
     * Récupérer les données d’un appareil spécifique appartenant à un tenant (Tenant Admin)
     */
    public List<TelemetryDataEntity> getTelemetryDataByDevice(String email, Long tenantId, Long deviceId) {
        verifierTenantAdmin(email, tenantId); // Vérification d'accès
        return telemetryDataRepository.findByDeviceIdAndTenantId(deviceId, tenantId);
    }


}
