package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.SystemStatus.SystemStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemStatusEntityRepository extends JpaRepository<SystemStatusEntity, Long> {
    List<SystemStatusEntity> findByDeviceName(String deviceName);
}