package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.Entities.Dashboard.DashboardEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant-admins/dashboards")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;


    @PostMapping("/tenant/{tenantId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public DashboardEntity createDashboard(@PathVariable Long tenantId, @RequestBody DashboardEntity dashboard) {
        return dashboardService.createDashboard(tenantId, dashboard);
    }


    @GetMapping("/tenant/{tenantId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public List<DashboardEntity> getDashboardsByTenant(@PathVariable Long tenantId) {
        return dashboardService.getDashboardsByTenant(tenantId);
    }


    @PutMapping("/customize/{dashboardId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<DashboardEntity> customizeDashboard(@PathVariable Long dashboardId, @RequestBody DashboardEntity dashboard) {
        DashboardEntity updatedDashboard = dashboardService.customizeDashboard(dashboardId, dashboard);
        return ResponseEntity.ok(updatedDashboard);
    }


    @DeleteMapping("/delete/{dashboardId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<String> deleteDashboard(@PathVariable Long dashboardId) {
        dashboardService.deleteDashboard(dashboardId);
        return ResponseEntity.ok("Dashboard supprimé avec succès");
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<List<DashboardEntity>> getUserDashboards(@PathVariable Long userId) {
        List<DashboardEntity> dashboards = dashboardService.getUserDashboards(userId);
        return ResponseEntity.ok(dashboards);
    }

}