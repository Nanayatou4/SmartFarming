package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantActivity;
import com.project.elevage.intelligent.Smart_Farming.Model.ServerMetrics;
import com.project.elevage.intelligent.Smart_Farming.Services.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins/monitoring")
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @GetMapping("/activities")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public List<TenantActivity> getTenantActivities() {
        return monitoringService.obtenirActiviteGlobale();
    }

    @GetMapping(value = "/server-stats", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    @ResponseBody
    public ServerMetrics getServerMetrics() {
        return monitoringService.obtenirPerformancesServeur();
    }

    @GetMapping("/logs")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public List<String> getLogs() {
        return monitoringService.analyserLogs();
    }

    @PostMapping("/resoudreIncident/{logId}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public String resoudreIncident(@PathVariable String logId) {
        return monitoringService.resoudreIncident(logId);
    }

}

