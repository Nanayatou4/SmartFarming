package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.Entities.Device.DeviceEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant-admins/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;


    @PostMapping("/add/{tenantId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public DeviceEntity addDeviceToTenant(@PathVariable Long tenantId, @RequestBody DeviceEntity device) {
        return deviceService.addDeviceToTenant(tenantId, device);
    }


    @PutMapping("/update/{deviceId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public DeviceEntity updateDevice(@PathVariable Long deviceId, @RequestBody DeviceEntity device) {
        return deviceService.updateDevice(deviceId, device);
    }


    @DeleteMapping("/delete/{deviceId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public void deleteDevice(@PathVariable Long deviceId) {
        deviceService.deleteDevice(deviceId);
    }


    @GetMapping("/tenant/{tenantId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public List<DeviceEntity> getDevicesByTenant(@PathVariable Long tenantId) {
        return deviceService.getDevicesByTenant(tenantId);
    }


    @GetMapping("devices/all")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public List<DeviceEntity> getAllDevices() {
        return deviceService.getAllDevices();
    }


    @PutMapping("/update-frequency/{deviceId}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public DeviceEntity updateDeviceFrequency(@PathVariable Long deviceId, @RequestBody int newFrequency) {
        return deviceService.updateDeviceFrequency(deviceId, newFrequency);
    }
}