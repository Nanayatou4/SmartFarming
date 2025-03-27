/*
package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.Entities.Plugin.PluginEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plugins")
public class PluginController {

    @Autowired
    private PluginService pluginService;

    // 🔹 1️⃣ Ajouter un plugin
    @PostMapping("/add")
    public ResponseEntity<PluginEntity> addPlugin(@RequestBody PluginEntity plugin) {
        PluginEntity newPlugin = pluginService.addPlugin(
                plugin.getName(),
                plugin.getDescription(),
                plugin.getVersion(),
                plugin.getConfiguration()
        );
        return ResponseEntity.ok(newPlugin);
    }

    // 🔹 2️⃣ Lister tous les plugins
    @GetMapping
    public ResponseEntity<List<PluginEntity>> getAllPlugins() {
        return ResponseEntity.ok(pluginService.getAllPlugins());
    }

    // 🔹 3️⃣ Activer un plugin
    @PutMapping("/activate/{id}")
    public ResponseEntity<PluginEntity> activatePlugin(@PathVariable Long id) {
        return ResponseEntity.ok(pluginService.activatePlugin(id));
    }

    // 🔹 4️⃣ Désactiver un plugin
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<PluginEntity> deactivatePlugin(@PathVariable Long id) {
        return ResponseEntity.ok(pluginService.deactivatePlugin(id));
    }

    // 🔹 5️⃣ Supprimer un plugin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlugin(@PathVariable Long id) {
        pluginService.deletePlugin(id);
        return ResponseEntity.noContent().build();
    }
}*/
