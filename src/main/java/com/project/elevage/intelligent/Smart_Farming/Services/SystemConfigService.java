package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.SystemConfig.SystemConfigEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.SystemConfigEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemConfigService {
    @Autowired
    private SystemConfigEntityRepository systemConfigRepository;

    // Modifier ou ajouter une configuration
//    public SystemConfigEntity mettreAJourConfig(String key, String value) {
//        Optional<SystemConfigEntity> configOpt = systemConfigRepository.findByKey(key);
//        SystemConfigEntity config = configOpt.orElse(new SystemConfigEntity());
////        config.setConfig_key(key);
////        config.setValue(value);
//        return systemConfigRepository.save(config);
//    }

    // Récupérer une configuration spécifique
//    public String obtenirConfig(String key) {
//        return systemConfigRepository.findByKey(key)
//                .map(SystemConfigEntity::getValue)
//                .orElse("Non défini");
//    }

    // Lister toutes les configurations
    public List<SystemConfigEntity> listerConfigurations() {
        return systemConfigRepository.findAll();
    }
}
