package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Device.DeviceEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.DeviceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceEntityRepository deviceRepository;

    /**
     * Ajouter un nouveau dispositif
     * @param device L'objet dispositif à ajouter
     * @return Le dispositif ajouté
     */
    public DeviceEntity addDevice(DeviceEntity device) {
        return deviceRepository.save(device);
    }

    /**
     * Modifier un dispositif existant
     * @param deviceId L'ID du dispositif à modifier
     * @param device L'objet dispositif mis à jour
     * @return Le dispositif mis à jour
     */
    public DeviceEntity updateDevice(Long deviceId, DeviceEntity device) {
        Optional<DeviceEntity> existingDevice = deviceRepository.findById(deviceId);
        if (existingDevice.isPresent()) {
            DeviceEntity updatedDevice = existingDevice.get();
            updatedDevice.setName(device.getName());  // Mise à jour de l'attribut name
            updatedDevice.setType(device.getType());  // Mise à jour du type
            // Ajouter d'autres mises à jour de champs selon ton modèle
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
