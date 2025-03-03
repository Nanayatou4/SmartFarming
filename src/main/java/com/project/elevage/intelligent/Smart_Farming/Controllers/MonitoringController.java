package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantActivity;
import com.project.elevage.intelligent.Smart_Farming.Model.ServerMetrics;
import com.project.elevage.intelligent.Smart_Farming.Services.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @GetMapping("/activite")
    public List<TenantActivity> obtenirActivite() {
        return monitoringService.obtenirActiviteGlobale();
    }

    @GetMapping("/serveur")
    public ServerMetrics obtenirPerformancesServeur() {
        return monitoringService.obtenirPerformancesServeur();
    }

    @GetMapping("/logs")
    public List<String> obtenirLogs() {
        return monitoringService.analyserLogs();
    }

    @PostMapping("/resoudreIncident/{logId}")
    public String resoudreIncident(@PathVariable String logId) {
        return monitoringService.resoudreIncident(logId);
    }
}

