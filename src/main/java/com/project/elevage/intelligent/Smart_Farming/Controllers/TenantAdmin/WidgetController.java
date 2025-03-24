package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.DTO.WidgetRequestDTO;
import com.project.elevage.intelligent.Smart_Farming.Entities.Widget.WidgetEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant-admins/widgets")
public class WidgetController {

    @Autowired
    private WidgetService widgetService;

    @PostMapping("/tenants/{tenantId}/dashboards/{dashboardId}/widgets")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<WidgetEntity> createWidget(
            @PathVariable Long tenantId,
            @PathVariable Long dashboardId,
            @RequestBody WidgetRequestDTO request) {

        WidgetEntity createdWidget = widgetService.createWidget(
                request.getTenantAdminId(), tenantId, dashboardId, request.getWidget());
        return ResponseEntity.ok(createdWidget);
    }

    @GetMapping("/tenants/{tenantId}/dashboards/{dashboardId}/widgets")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<List<WidgetEntity>> getWidgetsByDashboard(
            @PathVariable Long tenantId,
            @PathVariable Long dashboardId,
            @RequestBody WidgetRequestDTO request) {

        List<WidgetEntity> widgets = widgetService.getWidgetsByDashboard(
                request.getTenantAdminId(), tenantId, dashboardId);
        return ResponseEntity.ok(widgets);
    }

    @PutMapping("/tenants/{tenantId}/widgets/{widgetId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<WidgetEntity> updateWidget(
            @PathVariable Long tenantId,
            @PathVariable Long widgetId,
            @RequestBody WidgetRequestDTO request) {

        WidgetEntity updatedWidget = widgetService.updateWidget(
                request.getTenantAdminId(), tenantId, widgetId, request.getWidget());
        return ResponseEntity.ok(updatedWidget);
    }

    @DeleteMapping("/tenants/{tenantId}/widgets/{widgetId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<String> deleteWidget(
            @PathVariable Long tenantId,
            @PathVariable Long widgetId,
            @RequestBody WidgetRequestDTO request) {

        widgetService.deleteWidget(request.getTenantAdminId(), tenantId, widgetId);
        return ResponseEntity.ok("Widget supprimé avec succès");
    }

    @PutMapping("/{tenantId}/{widgetId}/enable")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<String> enableWidget(
            @PathVariable Long tenantId,
            @PathVariable Long widgetId,
            @RequestBody WidgetRequestDTO request) {
            widgetService.enableWidget(request.getTenantAdminId(), tenantId, widgetId);
            return ResponseEntity.ok("Widget réactivé avec succès.");
    }
}

