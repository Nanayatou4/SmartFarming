package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.DTO.AnalyticRequest;
import com.project.elevage.intelligent.Smart_Farming.Services.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tenant-admins/analytics")
public class AnalyticsController {

    @Autowired
    private  AnalyticsService analyticsService;


    @PostMapping("/temperature")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public Map<String, Double> getTenantAverageTemperature(@RequestBody AnalyticRequest request) {
        return analyticsService.getTenantAverageTemperature(request.getEmail(), request.getTenantId());
    }

    @PostMapping("/humidity")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public Map<String, Double> getTenantAverageHumidity(@RequestBody AnalyticRequest request) {
        return analyticsService.getTenantAverageHumidity(request.getEmail(), request.getTenantId());
    }
}




