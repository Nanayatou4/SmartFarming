package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;


import com.project.elevage.intelligent.Smart_Farming.Entities.Edge.EdgeDeviceEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.EdgeDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edge-devices")
public class EdgeDeviceController {

    @Autowired
    private EdgeDeviceService edgeDeviceService;

    /**
     * Ajouter un nouvel Edge Device
     */
    @PostMapping("/add")
    public ResponseEntity<EdgeDeviceEntity> addEdgeDevice(@RequestBody EdgeDeviceEntity device) {
        EdgeDeviceEntity savedDevice = edgeDeviceService.addEdgeDevice(device);
        return ResponseEntity.ok(savedDevice);
    }

    /**
     * Récupérer les Edge Devices d'un locataire
     */
    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<EdgeDeviceEntity>> getEdgeDevicesByTenant(@PathVariable Long tenantId) {
        List<EdgeDeviceEntity> deviceList = edgeDeviceService.getEdgeDevicesByTenant(tenantId);
        return ResponseEntity.ok(deviceList);
    }

    /**
     * Mettre à jour l'état de connexion d'un Edge Device
     */
    @PutMapping("/update-status/{deviceId}")
    public ResponseEntity<EdgeDeviceEntity> updateDeviceStatus(@PathVariable Long deviceId, @RequestParam boolean isConnected) {
        EdgeDeviceEntity updatedDevice = edgeDeviceService.updateDeviceStatus(deviceId, isConnected);
        return ResponseEntity.ok(updatedDevice);
    }

    @GetMapping("/gateways")
    public ResponseEntity<List<EdgeDeviceEntity>> getGateways() {
        List<EdgeDeviceEntity> gateways = edgeDeviceService.getGateways();
        return ResponseEntity.ok(gateways);
    }

}
