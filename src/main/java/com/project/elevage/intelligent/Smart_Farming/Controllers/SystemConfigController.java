package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.SystemConfig.SystemConfigEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins/system")
public class SystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    // Modifier ou ajouter une configuration
    @PostMapping("/configurer")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public SystemConfigEntity configurer(@RequestParam String key, @RequestParam String value) {
        return systemConfigService.updateConfig(key, value);
    }

    // Obtenir une configuration
    @GetMapping("/config/{key}")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public String obtenirConfig(@PathVariable String key) {
        return systemConfigService.getConfig(key);
    }

    // Lister toutes les configurations
    @GetMapping("/configs")
    @PreAuthorize("hasRole('ADMIN_SYSTEM')")
    public List<SystemConfigEntity> listerConfigs() {
        return systemConfigService.allConfigurations();
    }
}