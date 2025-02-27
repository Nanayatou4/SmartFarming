package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.Services.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // 🔹 1️⃣ Obtenir la température moyenne par dispositif
    @GetMapping("/temperature")
    public ResponseEntity<Map<String, Double>> getAverageTemperature() {
        return ResponseEntity.ok(analyticsService.getAverageTemperature());
    }

    // 🔹 2️⃣ Obtenir l’humidité moyenne par dispositif
    @GetMapping("/humidity")
    public ResponseEntity<Map<String, Double>> getAverageHumidity() {
        return ResponseEntity.ok(analyticsService.getAverageHumidity());
    }
}
