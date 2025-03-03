package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.ResourceEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.ResourceUsage;
import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.TenantResource;
import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    private ResourceService resourceService;

    @Autowired
    private SystemMonitoringService monitoringService;

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private TenantResourceService tenantResourceService;

    @Autowired
    private ResourceUsageService resourceUsageService;


    /// Les Tenants

    @PostMapping("/create-tenant")
    public ResponseEntity<TenantEntity> createTenant(@RequestBody TenantEntity tenant) {
        return ResponseEntity.ok(tenantService.createTenant(tenant));
    }

    @PutMapping("/update-tenant/{id}")
    public ResponseEntity<TenantEntity> updateTenant(@PathVariable Long id, @RequestBody TenantEntity tenant) {
        return ResponseEntity.ok(tenantService.updateTenant(id, tenant));
    }

    @DeleteMapping("/delete-tenant/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allTenants")
    public ResponseEntity<List<TenantEntity>> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }

    // ✅ Rechercher un Tenant par titre
    @GetMapping("/tenants/search")
    public ResponseEntity<List<TenantEntity>> searchTenants(@RequestParam String title) {
        return ResponseEntity.ok(tenantService.searchTenantsByTitle(title));
    }

    // ✅ Lister les Tenants avec Pagination
    @GetMapping("/tenants/paginated")
    public ResponseEntity<Page<TenantEntity>> getAllTenantsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(tenantService.getAllTenantsWithPagination(page, size));
    }

    // ✅ Activer/Désactiver un Tenant
    @PutMapping("/tenants/{id}/toggle-status")
    public ResponseEntity<TenantEntity> toggleTenantStatus(@PathVariable Long id, @RequestParam boolean isActive) {
        return ResponseEntity.ok(tenantService.toggleTenantStatus(id, isActive));
    }



    /// Les Tenants admin


    @PostMapping("/create-tenantAdmin/{tenantId}")
    public ResponseEntity<TenantAdminEntity> createTenantAdmin(@PathVariable Long tenantId, @RequestBody TenantAdminEntity admin) {
        return ResponseEntity.ok(tenantAdminService.createTenantAdmin(tenantId, admin));
    }

    @PutMapping("/update-tenantAdmin/{id}")
    public ResponseEntity<TenantAdminEntity> updateTenantAdmin(@PathVariable Long id, @RequestBody TenantAdminEntity admin) {
        return ResponseEntity.ok(tenantAdminService.updateTenantAdmin(id, admin));
    }

    @DeleteMapping("/delete-tenantAdmin/{id}")
    public ResponseEntity<Void> deleteTenantAdmin(@PathVariable Long id) {
        tenantAdminService.deleteTenantAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tenant-admins")
    public ResponseEntity<List<TenantAdminEntity>> getAllTenantAdmins() {
        return ResponseEntity.ok(tenantAdminService.getAllTenantAdmins());
    }

    // ✅ Filtrer les Tenant Admins par Tenant
    @GetMapping("/tenant-admins/filter")
    public ResponseEntity<List<TenantAdminEntity>> getTenantAdminsByTenant(@RequestParam Long tenantId) {
        return ResponseEntity.ok(tenantAdminService.getTenantAdminsByTenant(tenantId));
    }


    /// Les ressources


    // ✅ Attribuer une ressource à un Tenant
    @PostMapping("/tenants/{tenantId}/resources")
    public ResponseEntity<ResourceEntity> assignResource(
            @PathVariable Long tenantId,
            @RequestBody ResourceEntity resource) {
        return ResponseEntity.ok(resourceService.assignResourceToTenant(tenantId, resource));
    }

    // ✅ Modifier une ressource
    @PutMapping("/resources/{resourceId}")
    public ResponseEntity<ResourceEntity> updateResource(
            @PathVariable Long resourceId,
            @RequestBody ResourceEntity resource) {
        return ResponseEntity.ok(resourceService.updateResource(resourceId, resource));
    }

    // ✅ Supprimer une ressource
    @DeleteMapping("/resources/{resourceId}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long resourceId) {
        resourceService.deleteResource(resourceId);
        return ResponseEntity.noContent().build();
    }

    // ✅ Récupérer les ressources d’un Tenant
    @GetMapping("/tenants/{tenantId}/resources")
    public ResponseEntity<List<ResourceEntity>> getTenantResources(@PathVariable Long tenantId) {
        return ResponseEntity.ok(resourceService.getResourcesByTenant(tenantId));
    }


    /// System monitoring

    // Injection du service de surveillance système
    @Autowired
    private SystemMonitoringService systemMonitoringService;

    /**
     * Endpoint pour obtenir les statistiques du système (CPU, mémoire)
     * @return Les informations de surveillance système sous forme de map
     */
    @GetMapping("/stats")
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
    public String updateSystem() {
        return maintenanceService.updateSystem();
    }

    @PostMapping("/restart/{serviceName}")
    public String restartService(@PathVariable String serviceName) {
        return maintenanceService.restartService(serviceName);
    }


    /// Tenant Resource

    @PostMapping("/allocate")
    public TenantResource allocateResources(
            @RequestParam String tenantId,
            @RequestParam Long storageQuota,
            @RequestParam Integer maxDevices) {
        return tenantResourceService.allocateResources(tenantId, storageQuota, maxDevices);
    }

    @GetMapping("/{tenantId}/usage")
    public TenantResource getResourceUsage(@PathVariable String tenantId) {
        return tenantResourceService.getResourceUsage(tenantId);
    }

    @PostMapping("/{tenantId}/update-storage")
    public void updateUsedStorage(@PathVariable String tenantId, @RequestParam Long usedStorage) {
        tenantResourceService.updateUsedStorage(tenantId, usedStorage);
    }

    /// Resource Usage

    @GetMapping
    public List<ResourceUsage> getAllResourceUsages() {
        return resourceUsageService.getAllUsages();
    }

    @GetMapping("/{tenantId}/usageTenant")
    public List<ResourceUsage> getUsageByTenant(@PathVariable Long tenantId) {
        return resourceUsageService.getUsageByTenant(tenantId);
    }

    @PostMapping("/saveUsage")
    public ResourceUsage saveResourceUsage(@RequestBody ResourceUsage usage) {
        return resourceUsageService.saveUsage(usage);
    }
}
