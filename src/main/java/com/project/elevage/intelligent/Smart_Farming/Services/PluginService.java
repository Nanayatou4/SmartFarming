/*
package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Plugin.PluginEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.PluginEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PluginService {

    @Autowired
    private PluginEntityRepository pluginRepository;

    // Ajouter un plugin
    public PluginEntity addPlugin(String name, String description, String version, String config) {
        PluginEntity plugin = new PluginEntity(null, name, description, version, config, false);
        return pluginRepository.save(plugin);
    }

    // Lister tous les plugins
    public List<PluginEntity> getAllPlugins() {
        return pluginRepository.findAll();
    }

    // Activer un plugin
    public PluginEntity activatePlugin(Long pluginId) {
        Optional<PluginEntity> optionalPlugin = pluginRepository.findById(pluginId);
        if (optionalPlugin.isPresent()) {
            PluginEntity plugin = optionalPlugin.get();
            plugin.setActivated(true);
            return pluginRepository.save(plugin);
        }
        throw new RuntimeException("Plugin introuvable !");
    }

    // Désactiver un plugin
    public PluginEntity deactivatePlugin(Long pluginId) {
        Optional<PluginEntity> optionalPlugin = pluginRepository.findById(pluginId);
        if (optionalPlugin.isPresent()) {
            PluginEntity plugin = optionalPlugin.get();
            plugin.setActivated(false);
            return pluginRepository.save(plugin);
        }
        throw new RuntimeException("Plugin introuvable !");
    }

    // Supprimer un plugin
    public void deletePlugin(Long pluginId) {
        pluginRepository.deleteById(pluginId);
    }
}
*/
