package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.SystemStatus.SystemStatusEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.SystemStatusEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SystemStatusService {

    @Autowired
    private SystemStatusEntityRepository systemStatusRepository;

    public SystemStatusService(SystemStatusEntityRepository systemStatusRepository) {
        this.systemStatusRepository = systemStatusRepository;
    }

    // Ajouter un relevé de statut
    public SystemStatusEntity addSystemStatus(String deviceName, String status, double battery, double temperature, double humidity) {
        SystemStatusEntity systemStatus = new SystemStatusEntity(null, deviceName, status, battery, temperature, humidity, LocalDateTime.now());
        return systemStatusRepository.save(systemStatus);
    }

    // Lister tous les statuts du système
    public List<SystemStatusEntity> getAllStatuses() {
        return systemStatusRepository.findAll();
    }

    // Obtenir l’historique d’un dispositif
    public List<SystemStatusEntity> getDeviceHistory(String deviceName) {
        return systemStatusRepository.findByDeviceName(deviceName);
    }

    public void checkAlerts(SystemStatusEntity status) {
        if (status.getTemperature() > 50) {
            System.out.println("ALERTE : Température élevée sur " + status.getDeviceName());
        }
    }


}
