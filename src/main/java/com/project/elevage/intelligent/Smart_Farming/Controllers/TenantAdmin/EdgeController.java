package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;


import com.project.elevage.intelligent.Smart_Farming.DTO.EdgeUpdateDTO;
import com.project.elevage.intelligent.Smart_Farming.Entities.Edge.EdgeEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant-admins/edge")
public class EdgeController {

    @Autowired
    private EdgeService edgeService;

    /**
     * Ajouter un Edge pour un Tenant spécifique
     */
    @PostMapping("/tenant-admin/{tenantId}/add")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<EdgeEntity> addEdgeForTenant(@RequestParam String tenantAdminEmail, @PathVariable Long tenantId, @RequestBody EdgeEntity device) {
        EdgeEntity newDevice = edgeService.addEdgeDevice(tenantAdminEmail, tenantId, device);
        return new ResponseEntity<>(newDevice, HttpStatus.CREATED);
    }

    /**
     * Récupérer les Edge d'un Tenant spécifique
     */
    @GetMapping("/tenant-admin/{tenantId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<List<EdgeEntity>> getEdgeByTenant(@RequestParam String tenantAdminEmail, @PathVariable Long tenantId) {
        List<EdgeEntity> devices = edgeService.getEdgeByTenant(tenantAdminEmail, tenantId);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    /**
     * Supprimer un Edge d'un Tenant spécifique
     */
    @DeleteMapping("/tenant-admin/{tenantId}/delete/{deviceId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<Void> deleteEdge(@RequestParam String tenantAdminEmail, @PathVariable Long tenantId, @PathVariable Long deviceId) {
        edgeService.deleteEdge(tenantAdminEmail, tenantId, deviceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Mettre à jour un Edge pour un Tenant spécifique
     */
    @PutMapping("/tenant-admin/{tenantId}/update/{deviceId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public ResponseEntity<EdgeEntity> updateEdge(@RequestParam String tenantAdminEmail, @PathVariable Long tenantId, @PathVariable Long deviceId, @RequestBody EdgeUpdateDTO updatedDevice) {
        EdgeEntity updatedEdgeDevice = edgeService.updateEdge(tenantAdminEmail, tenantId, deviceId, updatedDevice);
        return new ResponseEntity<>(updatedEdgeDevice, HttpStatus.OK);
    }
}




