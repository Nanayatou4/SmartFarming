package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.DTO.EdgeUpdateDTO;
import com.project.elevage.intelligent.Smart_Farming.Entities.Edge.EdgeEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.EdgeDeviceEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantAdminEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EdgeService {

    @Autowired
    private EdgeDeviceEntityRepository edgeDeviceRepository;

    @Autowired
    private TenantAdminEntityRepository tenantAdminRepository;

    @Autowired
    private TenantEntityRepository tenantRepository;


    ///  *******************ADMIN SYSTEM************************

    /**
     * Ajouter un nouvel Edge
     */
    public EdgeEntity addEdge(EdgeEntity device) {
        device.setLastSync(LocalDateTime.now()); // Date actuelle de synchronisation
        return edgeDeviceRepository.save(device);
    }

    /**
     * Récupérer tous les Edge d'un locataire
     */
    public List<EdgeEntity> getEdgeByTenant(Long tenantId) {
        return edgeDeviceRepository.findByTenantId(tenantId);
    }

    /**
     * Mettre à jour l'état de connexion d'un Edge
     */
    public EdgeEntity updateEdgeStatus(Long deviceId, boolean isConnected , boolean isGateway) {
        EdgeEntity device = edgeDeviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Edge Device non trouvé"));

        if (device.isConnected() != isConnected) {
            device.setConnected(isConnected);
        }

        if (device.isGateway() != isGateway) {
            device.setGateway(isGateway);
        }

        // Mettre à jour la dernière synchronisation si l'état de connexion a changé
        device.setLastSync(LocalDateTime.now());

        // Sauvegarder les modifications sans affecter les autres champs
        return edgeDeviceRepository.save(device);
    }


    public List<EdgeEntity> getGateways() {
        return edgeDeviceRepository.findByIsGateway(true);
    }


    ///  **********************TENANT ADMIN**************************

    private TenantEntity verifyTenantAdmin(String tenantAdminEmail, Long tenantId) {
        // Récupérer le Tenant Admin par son email
        TenantAdminEntity tenantAdmin = tenantAdminRepository.findByEmail(tenantAdminEmail)
                .orElseThrow(() -> new RuntimeException("Tenant Admin non trouvé"));

        // Vérifier que le Tenant Admin appartient au Tenant spécifié
        if (!tenantAdmin.getTenant().getId().equals(tenantId)) {
            throw new RuntimeException("Ce Tenant Admin n'est pas associé au Tenant spécifié.");
        }

        return tenantAdmin.getTenant();
    }

    /**
     * Ajouter un Edge pour un Tenant spécifique
     */
    public EdgeEntity addEdgeDevice(String tenantAdminEmail, Long tenantId, EdgeEntity device) {
        // Vérifier si le Tenant Admin appartient au Tenant
        TenantEntity tenant = verifyTenantAdmin(tenantAdminEmail, tenantId);

        // Ajouter un Edge Device pour le Tenant
        device.setTenant(tenant);
        device.setLastSync(LocalDateTime.now()); // Date actuelle de synchronisation
        return edgeDeviceRepository.save(device);
    }

    /**
     * Récupérer les Edge  d'un Tenant spécifique
     */
    public List<EdgeEntity> getEdgeByTenant(String tenantAdminEmail, Long tenantId) {
        // Vérifier si le Tenant Admin appartient au Tenant
        verifyTenantAdmin(tenantAdminEmail, tenantId);

        // Récupérer les Edge Devices du Tenant
        return edgeDeviceRepository.findByTenantId(tenantId);
    }

    /**
     * Supprimer un Edge  d'un Tenant spécifique
     */
    public void deleteEdge(String tenantAdminEmail, Long tenantId, Long deviceId) {
        // Vérifier si le Tenant Admin appartient au Tenant
        verifyTenantAdmin(tenantAdminEmail, tenantId);

        // Supprimer le Edge Device
        edgeDeviceRepository.deleteById(deviceId);
    }

    /**
     * Mettre à jour un Edge  pour un Tenant spécifique
     */
    public EdgeEntity updateEdge(String tenantAdminEmail, Long tenantId, Long deviceId, EdgeUpdateDTO updatedDevice) {
        // Vérifier si le Tenant Admin appartient au Tenant
        TenantEntity tenant = verifyTenantAdmin(tenantAdminEmail, tenantId);

        // Vérifier si le Edge Device existe
        EdgeEntity existingDevice = edgeDeviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Edge Device non trouvé"));

        // Vérifier que le Edge Device appartient au Tenant spécifié
        if (!existingDevice.getTenant().getId().equals(tenantId)) {
            throw new RuntimeException("Cet Edge Device ne appartient pas au Tenant spécifié.");
        }

        // Mettre à jour uniquement les champs non nuls
        if (updatedDevice.getName() != null) {
            existingDevice.setName(updatedDevice.getName());
        }
        if (updatedDevice.getType() != null) {
            existingDevice.setType(updatedDevice.getType());
        }
        if (updatedDevice.getIpAddress() != null) {
            existingDevice.setIpAddress(updatedDevice.getIpAddress());
        }
        if (updatedDevice.getIsConnected() != null) {
            existingDevice.setConnected(updatedDevice.getIsConnected());
        }
        if (updatedDevice.getIsGateway() != null) {
            existingDevice.setGateway(updatedDevice.getIsGateway());
        }

        // Mettre à jour la dernière synchronisation
        existingDevice.setLastSync(LocalDateTime.now());

        // Sauvegarder les modifications
        return edgeDeviceRepository.save(existingDevice);
    }









}
