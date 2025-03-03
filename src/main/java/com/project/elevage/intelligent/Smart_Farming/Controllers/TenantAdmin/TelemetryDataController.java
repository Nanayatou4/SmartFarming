package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.Entities.Telemetrie.TelemetryDataEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.TelemetryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telemetry")
public class TelemetryDataController {

    @Autowired
    private TelemetryDataService telemetryDataService;

    /**
     * Enregistrer une nouvelle donnée de télémétrie
     */
    @PostMapping("/create")
    public ResponseEntity<TelemetryDataEntity> saveTelemetryData(@RequestBody TelemetryDataEntity data) {
        TelemetryDataEntity savedData = telemetryDataService.saveTelemetryData(data);
        return ResponseEntity.ok(savedData);
    }

    /**
     * Récupérer toutes les données de télémétrie
     */
    @GetMapping("/all")
    public ResponseEntity<List<TelemetryDataEntity>> getAllTelemetryData() {
        List<TelemetryDataEntity> dataList = telemetryDataService.getAllTelemetryData();
        return ResponseEntity.ok(dataList);
    }

    /**
     * Récupérer les données d'un appareil spécifique
     */
    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<TelemetryDataEntity>> getTelemetryDataByDevice(@PathVariable Long deviceId) {
        List<TelemetryDataEntity> dataList = telemetryDataService.getTelemetryDataByDevice(deviceId);
        return ResponseEntity.ok(dataList);
    }
}

