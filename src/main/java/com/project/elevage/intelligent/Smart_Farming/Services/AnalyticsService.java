package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.SystemStatus.SystemStatusEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.SystemStatusEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    @Autowired
    private SystemStatusEntityRepository systemStatusRepository;

    public AnalyticsService(SystemStatusEntityRepository systemStatusRepository) {
        this.systemStatusRepository = systemStatusRepository;
    }

    // Moyenne de température par dispositif
    public Map<String, Double> getAverageTemperature() {
        List<SystemStatusEntity> statuses = systemStatusRepository.findAll();
        return statuses.stream()
                .collect(Collectors.groupingBy(
                        SystemStatusEntity::getDeviceName,
                        Collectors.averagingDouble(SystemStatusEntity::getTemperature)
                ));
    }

    // Moyenne d’humidité par dispositif
    public Map<String, Double> getAverageHumidity() {
        List<SystemStatusEntity> statuses = systemStatusRepository.findAll();
        return statuses.stream()
                .collect(Collectors.groupingBy(
                        SystemStatusEntity::getDeviceName,
                        Collectors.averagingDouble(SystemStatusEntity::getHumidity)
                ));
    }
}
