package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Device.DeviceEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.DeviceEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.TenantEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceEntityRepository deviceRepository;

    @Autowired
    private TenantEntityRepository tenantRepository;


    /**
     * Ajouter un dispositif et l'associer à un tenant
     */
    public DeviceEntity addDeviceToTenant(Long tenantId, DeviceEntity device) {
        // Récupérer le tenant correspondant à l'ID
        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant non trouvé"));

        // Associer le device au tenant
        device.setTenant(tenant);

        // Sauvegarder le device avec la relation au tenant
        return deviceRepository.save(device);
    }

    /**
     * Modifier un dispositif existant
     */
    public DeviceEntity updateDevice(Long deviceId, DeviceEntity device) {
        Optional<DeviceEntity> existingDevice = deviceRepository.findById(deviceId);
        if (existingDevice.isPresent()) {
            DeviceEntity updatedDevice = existingDevice.get();
            updatedDevice.setName(device.getName());
            updatedDevice.setType(device.getType());
            updatedDevice.setFrequency(device.getFrequency());
            updatedDevice.setDeviceToken(device.getDeviceToken());
            return deviceRepository.save(updatedDevice);
        }
        throw new RuntimeException("Dispositif non trouvé");
    }

    /**
     * Supprimer un dispositif
     * @param deviceId L'ID du dispositif à supprimer
     */
    public void deleteDevice(Long deviceId) {
        Optional<DeviceEntity> device = deviceRepository.findById(deviceId);
        if (device.isPresent()) {
            deviceRepository.delete(device.get());
        } else {
            throw new RuntimeException("Dispositif non trouvé");
        }
    }

    /**
     * Récupérer la liste des dispositifs d'un tenant spécifique
     * @param tenantId L'ID du tenant
     * @return Liste des dispositifs
     */
    public List<DeviceEntity> getDevicesByTenant(Long tenantId) {
        // Récupérer le tenant pour vérifier la validité
        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant non trouvé"));

        // Retourner la liste des dispositifs associés à ce tenant
        return deviceRepository.findByTenant(tenant);
    }

    /**
     * Récupérer la liste des dispositifs
     * @return Liste des dispositifs
     */
    public List<DeviceEntity> getAllDevices() {
        return deviceRepository.findAll();
    }

    /**
     * Modifier la fréquence d’envoi d’un appareil
     */
    public DeviceEntity updateDeviceFrequency(Long deviceId, int newFrequency) {
        DeviceEntity device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Appareil non trouvé"));
        device.setFrequency(newFrequency);
        return deviceRepository.save(device);
    }

}
