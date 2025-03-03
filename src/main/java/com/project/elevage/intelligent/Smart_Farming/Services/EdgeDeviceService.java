package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Edge.EdgeDeviceEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.EdgeDeviceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EdgeDeviceService {

    @Autowired
    private EdgeDeviceEntityRepository edgeDeviceRepository;

    /**
     * Ajouter un nouvel Edge Device
     */
    public EdgeDeviceEntity addEdgeDevice(EdgeDeviceEntity device) {
        device.setLastSync(LocalDateTime.now()); // Date actuelle de synchronisation
        return edgeDeviceRepository.save(device);
    }

    /**
     * Récupérer tous les Edge Devices d'un locataire
     */
    public List<EdgeDeviceEntity> getEdgeDevicesByTenant(Long tenantId) {
        return edgeDeviceRepository.findByTenantId(tenantId);
    }

    /**
     * Mettre à jour l'état de connexion d'un Edge Device
     */
    public EdgeDeviceEntity updateDeviceStatus(Long deviceId, boolean isConnected) {
        EdgeDeviceEntity device = edgeDeviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Edge Device non trouvé"));
        device.setConnected(isConnected);
        device.setLastSync(LocalDateTime.now());
        return edgeDeviceRepository.save(device);
    }

    public List<EdgeDeviceEntity> getGateways() {
        return edgeDeviceRepository.findByIsGateway(true);
    }

}
