package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.Entities.Alarme.AlarmEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alarms")
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    /**
     * Créer une alarme
     */
    @PostMapping("/create")
    public ResponseEntity<AlarmEntity> createAlarm(@RequestBody AlarmEntity alarm) {
        AlarmEntity newAlarm = alarmService.createAlarm(alarm);
        return ResponseEntity.ok(newAlarm);
    }

    /**
     * Récupérer toutes les alarmes
     */
    @GetMapping("/all")
    public ResponseEntity<List<AlarmEntity>> getAllAlarms() {
        List<AlarmEntity> alarms = alarmService.getAllAlarms();
        return ResponseEntity.ok(alarms);
    }

    /**
     * Récupérer les alarmes d'un appareil
     */
    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<AlarmEntity>> getAlarmsByDevice(@PathVariable Long deviceId) {
        List<AlarmEntity> alarms = alarmService.getAlarmsByDevice(deviceId);
        return ResponseEntity.ok(alarms);
    }

    /**
     * Récupérer les alarmes actives
     */
    @GetMapping("/active")
    public ResponseEntity<List<AlarmEntity>> getActiveAlarms() {
        List<AlarmEntity> alarms = alarmService.getActiveAlarms();
        return ResponseEntity.ok(alarms);
    }

    /**
     * Résoudre une alarme
     */
    @PutMapping("/resolve/{alarmId}")
    public ResponseEntity<AlarmEntity> resolveAlarm(@PathVariable Long alarmId) {
        AlarmEntity resolvedAlarm = alarmService.resolveAlarm(alarmId);
        return ResponseEntity.ok(resolvedAlarm);
    }
}