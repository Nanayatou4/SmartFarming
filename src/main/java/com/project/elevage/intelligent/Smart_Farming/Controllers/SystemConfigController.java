package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.SystemConfig.SystemConfigEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/system")
public class SystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    // Modifier ou ajouter une configuration
    @PostMapping("/configurer")
    public SystemConfigEntity configurer(@RequestParam String key, @RequestParam String value) {
        return systemConfigService.mettreAJourConfig(key, value);
    }

    // Obtenir une configuration
    @GetMapping("/config/{key}")
    public String obtenirConfig(@PathVariable String key) {
        return systemConfigService.obtenirConfig(key);
    }

    // Lister toutes les configurations
    @GetMapping("/configs")
    public List<SystemConfigEntity> listerConfigs() {
        return systemConfigService.listerConfigurations();
    }
}