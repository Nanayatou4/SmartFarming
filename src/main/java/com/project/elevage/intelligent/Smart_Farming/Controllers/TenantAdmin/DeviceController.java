package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.Entities.Device.DeviceEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    /**
     * Ajouter un nouveau dispositif
     * @param device L'objet dispositif à ajouter
     * @return Réponse avec l'objet dispositif ajouté
     */
    @PostMapping("/add")
    public ResponseEntity<DeviceEntity> addDevice(@RequestBody DeviceEntity device) {
        DeviceEntity newDevice = deviceService.addDevice(device);
        return ResponseEntity.ok(newDevice);
    }

    /**
     * Modifier un dispositif existant
     * @param deviceId L'ID du dispositif à modifier
     * @param device L'objet dispositif mis à jour
     * @return Réponse avec l'objet dispositif mis à jour
     */
    @PutMapping("/update/{deviceId}")
    public ResponseEntity<DeviceEntity> updateDevice(@PathVariable Long deviceId, @RequestBody DeviceEntity device) {
        DeviceEntity updatedDevice = deviceService.updateDevice(deviceId, device);
        return ResponseEntity.ok(updatedDevice);
    }

    /**
     * Supprimer un dispositif
     * @param deviceId L'ID du dispositif à supprimer
     * @return Réponse avec un message de confirmation
     */
    @DeleteMapping("/delete/{deviceId}")
    public ResponseEntity<String> deleteDevice(@PathVariable Long deviceId) {
        deviceService.deleteDevice(deviceId);
        return ResponseEntity.ok("Dispositif supprimé avec succès");
    }

    /**
     * Récupérer la liste des dispositifs
     * @return Liste des dispositifs
     */
    @GetMapping("/list")
    public ResponseEntity<List<DeviceEntity>> getAllDevices() {
        List<DeviceEntity> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    /**
     * Modifier la fréquence d’envoi d’un appareil
     */
    @PutMapping("/updateFrequency/{deviceId}")
    public ResponseEntity<DeviceEntity> updateDeviceFrequency(@PathVariable Long deviceId, @RequestParam int frequency) {
        DeviceEntity updatedDevice = deviceService.updateDeviceFrequency(deviceId, frequency);
        return ResponseEntity.ok(updatedDevice);
    }

}