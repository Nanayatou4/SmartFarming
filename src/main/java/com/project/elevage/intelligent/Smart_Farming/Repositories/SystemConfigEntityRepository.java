package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.SystemConfig.SystemConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemConfigEntityRepository extends JpaRepository<SystemConfigEntity, Long> {
    Optional<SystemConfigEntity> findByKey(String key);
}