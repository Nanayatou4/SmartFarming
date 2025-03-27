package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.DTO.EdgeUpdateDTO;
import com.project.elevage.intelligent.Smart_Farming.Entities.Asset.AssetEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Dashboard.DashboardEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Device.DeviceEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Edge.EdgeEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.ResourceEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.ResourceUsage;
import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.TenantResource;
import com.project.elevage.intelligent.Smart_Farming.Entities.Telemetrie.TelemetryDataEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Widget.WidgetEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admins")
public class AdminSystemController {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private TenantAdminService tenantAdminService;

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private AssetService assetService;

    @Autowired
    private EdgeService edgeService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ResourceManagementService resourceManagementService;

    @Autowired
    private WidgetService widgetService;

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private TelemetryDataService telemetryDataService;

    @Autowired
    private RateLimiterService rateLimiterService;


    /// Les Tenants

    @PostMapping("/create-tenant")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<TenantEntity> createTenant(@RequestBody TenantEntity tenant) {
        return ResponseEntity.ok(tenantService.createTenant(tenant));
    }

    @PutMapping("/update-tenant/{id}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<TenantEntity> updateTenant(@PathVariable Long id, @RequestBody TenantEntity tenant) {
        return ResponseEntity.ok(tenantService.updateTenant(id, tenant));
    }

    @DeleteMapping("/delete-tenant/{id}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allTenants")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<List<TenantEntity>> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }

    // Rechercher un Tenant par titre
    @GetMapping("/tenants/search")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<List<TenantEntity>> searchTenants(@RequestParam String title) {
        return ResponseEntity.ok(tenantService.searchTenantsByTitle(title));
    }

    // Lister les Tenants avec Pagination
    @GetMapping("/tenants/paginated")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<Page<TenantEntity>> getAllTenantsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(tenantService.getAllTenantsWithPagination(page, size));
    }

    // Activer/Désactiver un Tenant
    @PutMapping("/tenants/{id}/toggle-status")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<TenantEntity> toggleTenantStatus(@PathVariable Long id, @RequestParam boolean isActive) {
        return ResponseEntity.ok(tenantService.toggleTenantStatus(id, isActive));
    }



    /// Les Tenants admin


    @PostMapping("/create-tenantAdmin/{tenantId}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<TenantAdminEntity> createTenantAdmin(@PathVariable Long tenantId, @RequestBody TenantAdminEntity admin) {
        return ResponseEntity.ok(tenantAdminService.createTenantAdmin(tenantId, admin));
    }

    @PutMapping("/update-tenantAdmin/{id}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<TenantAdminEntity> updateTenantAdminBySystem(
            @PathVariable Long id, @RequestBody TenantAdminEntity updatedAdmin) {
        TenantAdminEntity admin = tenantAdminService.updateTenantAdminBySystem(id, updatedAdmin);
        return ResponseEntity.ok(admin);
    }

    @DeleteMapping("/delete-tenantAdmin/{id}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<Void> deleteTenantAdmin(@PathVariable Long id) {
        tenantAdminService.deleteTenantAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tenant-admins")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<List<TenantAdminEntity>> getAllTenantAdmins() {
        return ResponseEntity.ok(tenantAdminService.getAllTenantAdmins());
    }

    //  Filtrer les Tenant Admins par Tenant
    @GetMapping("/tenant-admins/filter")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<List<TenantAdminEntity>> getTenantAdminsByTenant(@RequestParam Long tenantId) {
        return ResponseEntity.ok(tenantAdminService.getTenantAdminsByTenant(tenantId));
    }


    /// Les ressources


    //  Attribuer une ressource à un Tenant
    @PostMapping("/tenants/{tenantId}/resources")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<ResourceEntity> assignResource(
            @PathVariable Long tenantId,
            @RequestBody ResourceEntity resource) {
        return ResponseEntity.ok(resourceManagementService.assignResourceToTenant(tenantId, resource));
    }

    //  Modifier une ressource
    @PutMapping("/resources/{resourceId}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<ResourceEntity> updateResource(
            @PathVariable Long resourceId,
            @RequestBody ResourceEntity resource) {
        return ResponseEntity.ok(resourceManagementService.updateResource(resourceId, resource));
    }

    // Supprimer une ressource
    @DeleteMapping("/resources/{resourceId}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<Void> deleteResource(@PathVariable Long resourceId) {
        resourceManagementService.deleteResource(resourceId);
        return ResponseEntity.noContent().build();
    }

    // Récupérer les ressources d’un Tenant
    @GetMapping("/tenants/{tenantId}/resources")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<List<ResourceEntity>> getTenantResources(@PathVariable Long tenantId) {
        return ResponseEntity.ok(resourceManagementService.getResourcesByTenant(tenantId));
    }


    /// System monitoring

    @Autowired
    private SystemMonitoringService systemMonitoringService;


    @GetMapping("/stats")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public Map<String, Object> getSystemStats() {
        return Map.of(
                "cpuUsage", systemMonitoringService.getCpuUsage(),
                "freeMemory", systemMonitoringService.getFreeMemory(),
                "totalMemory", systemMonitoringService.getTotalMemory(),
                "memoryUsage", systemMonitoringService.getMemoryUsage()
        );
    }

    /// Maintenance

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public String updateSystem() {
        return maintenanceService.updateSystem();
    }

    @PostMapping("/restart/{serviceName}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public String restartService(@PathVariable String serviceName) {
        return maintenanceService.restartService(serviceName);
    }


    /// Tenant Resource

    @PostMapping("/allocate")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public TenantResource allocateResources(
            @RequestBody TenantResource tenantResource) {
        return resourceManagementService.allocateResources(
                tenantResource.getTenant().getId(),
                tenantResource.getStorageQuota(),
                tenantResource.getMaxDevices());
    }


    @GetMapping("/{tenantId}/resource-usage")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public TenantResource getResourceUsage(@PathVariable Long tenantId) {
        return resourceManagementService.getResourceUsage(tenantId);
    }


    @PutMapping("/{tenantId}/update-storage")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public void updateUsedStorage(@PathVariable Long tenantId, @RequestParam Long usedStorage) {
        resourceManagementService.updateUsedStorage(tenantId, usedStorage);
    }


    /// Resource Usage

    @GetMapping("/resource-usages")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public List<ResourceUsage> getAllResourceUsages() {
        return resourceManagementService.getAllUsages();
    }

    @GetMapping("/{tenantId}/usage-tenant")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public List<ResourceUsage> getUsageByTenant(@PathVariable Long tenantId) {
        return resourceManagementService.getUsageByTenant(tenantId);
    }

    @PostMapping("/resource-usage")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResourceUsage saveResourceUsage(@RequestBody ResourceUsage usage) {
        return resourceManagementService.saveUsage(usage);
    }

    /// Asset
    @PostMapping("assets/add/{tenantId}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<AssetEntity> addAssetToTenant(@RequestBody AssetEntity asset, @PathVariable Long tenantId) {
        try {
            AssetEntity createdAsset = assetService.addAssetToTenant(tenantId, asset);
            return new ResponseEntity<>(createdAsset, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("assets/update/{assetId}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<AssetEntity> updateAsset(@PathVariable Long assetId, @RequestBody AssetEntity asset) {
        try {
            AssetEntity updatedAsset = assetService.updateAsset(assetId, asset);
            return new ResponseEntity<>(updatedAsset, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("assets/delete/{assetId}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long assetId) {
        try {
            assetService.deleteAsset(assetId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("assets/all")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<List<AssetEntity>> getAllAssets() {
        List<AssetEntity> assets = assetService.getAllAssets();
        return new ResponseEntity<>(assets, HttpStatus.OK);
    }

    /// EDGE

    @PostMapping("/edge-devices/add")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<EdgeEntity> addEdgeDevice(@RequestBody EdgeEntity device) {
        EdgeEntity savedDevice = edgeService.addEdge(device);
        return ResponseEntity.ok(savedDevice);
    }

    @GetMapping("/edge-devices/tenant/{tenantId}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<List<EdgeEntity>> getEdgeDevicesByTenant(@PathVariable Long tenantId) {
        List<EdgeEntity> deviceList = edgeService.getEdgeByTenant(tenantId);
        return ResponseEntity.ok(deviceList);
    }


    @PutMapping("/edge-devices/update-status/{deviceId}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<EdgeEntity> updateDeviceStatus(@PathVariable Long deviceId, @RequestBody EdgeUpdateDTO edgeDeviceUpdateDTO) {
        EdgeEntity updatedDevice = edgeService.updateEdgeStatus(deviceId, edgeDeviceUpdateDTO.getIsConnected(), edgeDeviceUpdateDTO.getIsGateway());
        return ResponseEntity.ok(updatedDevice);
    }

    @GetMapping("/edge-devices/gateways")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<List<EdgeEntity>> getGateways() {
        List<EdgeEntity> gateways = edgeService.getGateways();
        return ResponseEntity.ok(gateways);
    }

    /// DEVICE

    @GetMapping("/devices/all")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public List<DeviceEntity> getAllDevices() {
        return deviceService.getAllDevices();
    }

    /// Dashboard

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<List<DashboardEntity>> getAllDashboards() {
        List<DashboardEntity> dashboards = dashboardService.getAllDashboardsForAdmin();
        return ResponseEntity.ok(dashboards);
    }

    /// Widget

    @GetMapping("/widgets/all")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<List<WidgetEntity>> getAllWidgets() {
        return ResponseEntity.ok(widgetService.getAllWidgets());
    }

    @PutMapping("/widgets/disable/{widgetId}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<String> disableWidget(@PathVariable Long widgetId) {
        widgetService.disableWidget(widgetId);
        return ResponseEntity.ok("Widget désactivé avec succès");
    }

    @PutMapping("/{widgetId}/enable")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<String> enableWidget(@PathVariable Long widgetId) {
        try {
            widgetService.enableWidget(widgetId);
            return ResponseEntity.ok("Widget réactivé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la réactivation du widget : " + e.getMessage());
        }
    }

    /// Analytic
    @GetMapping("analytic/temperature")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<Map<String, Double>> getGlobalTemperature() {
        return ResponseEntity.ok(analyticsService.getGlobalAverageTemperature());
    }

    @GetMapping("analytic/humidity")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<Map<String, Double>> getGlobalHumidity() {
        return ResponseEntity.ok(analyticsService.getGlobalAverageHumidity());
    }

    /// Telemetry

    @PostMapping("telemetry/create")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<?> saveTelemetryData(@RequestBody TelemetryDataEntity data) {
        if (!rateLimiterService.tryConsume()) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Trop de requêtes ! Veuillez réessayer plus tard.");
        }
        TelemetryDataEntity savedData = telemetryDataService.saveTelemetryData(data);
        return ResponseEntity.ok(savedData);
    }

    @GetMapping("telemetry/all")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<?> getAllTelemetryData() {
        if (!rateLimiterService.tryConsume()) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Trop de requêtes ! Veuillez réessayer plus tard.");
        }
        List<TelemetryDataEntity> dataList = telemetryDataService.getAllTelemetryData();
        return ResponseEntity.ok(dataList);
    }


    @GetMapping("telemetry/device/{deviceId}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public ResponseEntity<?> getTelemetryDataByDevice(@PathVariable Long deviceId) {
        if (!rateLimiterService.tryConsume()) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Trop de requêtes ! Veuillez réessayer plus tard.");
        }
        List<TelemetryDataEntity> dataList = telemetryDataService.getTelemetryDataByDevice(deviceId);
        return ResponseEntity.ok(dataList);
    }
}
