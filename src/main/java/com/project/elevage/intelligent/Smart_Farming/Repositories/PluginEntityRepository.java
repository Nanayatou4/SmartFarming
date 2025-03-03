package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Plugin.PluginEntity;
import com.sun.source.util.Plugin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PluginEntityRepository extends JpaRepository<PluginEntity, Long> {
    Optional<Plugin> findByName(String name);
}