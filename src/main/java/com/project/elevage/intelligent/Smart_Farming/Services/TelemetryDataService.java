package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Telemetrie.TelemetryDataEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TelemetryDataEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TelemetryDataService {

    @Autowired
    private TelemetryDataEntityRepository telemetryDataRepository;

    /**
     * Enregistrer une nouvelle donnée de télémétrie
//     */
//    public TelemetryDataEntity saveTelemetryData(TelemetryDataEntity data) {
//        data.setTimestamp(LocalDateTime.now()); // Date actuelle
//        return telemetryDataRepository.save(data);
//    }

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
}
