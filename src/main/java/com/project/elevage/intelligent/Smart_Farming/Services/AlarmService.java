package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Alarme.AlarmEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Alarme.AlarmStatus;
import com.project.elevage.intelligent.Smart_Farming.Repositories.AlarmEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlarmService {

    @Autowired
    private AlarmEntityRepository alarmRepository;

    /**
     * Créer une alarme
     */
    public AlarmEntity createAlarm(AlarmEntity alarm) {
        alarm.setTimestamp(LocalDateTime.now()); // Date actuelle
        alarm.setStatus(AlarmStatus.ACTIVE); // L'alarme est active par défaut
        return alarmRepository.save(alarm);
    }

    /**
     * Récupérer toutes les alarmes
     */
    public List<AlarmEntity> getAllAlarms() {
        return alarmRepository.findAll();
    }

    /**
     * Récupérer les alarmes par appareil
     */
    public List<AlarmEntity> getAlarmsByDevice(Long deviceId) {
        return alarmRepository.findByDeviceId(deviceId);
    }

    /**
     * Récupérer les alarmes actives
     */
    public List<AlarmEntity> getActiveAlarms() {
        return alarmRepository.findByStatus("ACTIVE");
    }

    /**
     * Marquer une alarme comme résolue
     */
    public AlarmEntity resolveAlarm(Long alarmId) {
        AlarmEntity alarm = alarmRepository.findById(alarmId)
                .orElseThrow(() -> new RuntimeException("Alarme non trouvée"));
        alarm.setStatus(AlarmStatus.RESOLUE);
        return alarmRepository.save(alarm);
    }

}
