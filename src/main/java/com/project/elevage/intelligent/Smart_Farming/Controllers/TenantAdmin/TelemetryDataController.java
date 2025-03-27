package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.DTO.TelemetryRequest;
import com.project.elevage.intelligent.Smart_Farming.Entities.Telemetrie.TelemetryDataEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.RateLimiterService;
import com.project.elevage.intelligent.Smart_Farming.Services.TelemetryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant-admins/telemetry")
public class TelemetryDataController {

    @Autowired
    private TelemetryDataService telemetryDataService;

    @Autowired
    private RateLimiterService rateLimiterService; // Ajout du rate limiter

    @PostMapping("/tenant")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<?> getTelemetryDataByTenant(@RequestBody TelemetryRequest request) {
        if (!rateLimiterService.tryConsume()) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Trop de requêtes ! Veuillez réessayer plus tard.");
        }
        List<TelemetryDataEntity> data = telemetryDataService.getTelemetryDataByTenant(
                request.getEmail(), request.getTenantId());
        return ResponseEntity.ok(data);
    }

    @PostMapping("/device")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<?> getTelemetryDataByDevice(@RequestBody TelemetryRequest request) {
        if (!rateLimiterService.tryConsume()) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Trop de requêtes ! Veuillez réessayer plus tard.");
        }
        List<TelemetryDataEntity> data = telemetryDataService.getTelemetryDataByDevice(
                request.getEmail(), request.getTenantId(), request.getDeviceId());
        return ResponseEntity.ok(data);
    }
}

