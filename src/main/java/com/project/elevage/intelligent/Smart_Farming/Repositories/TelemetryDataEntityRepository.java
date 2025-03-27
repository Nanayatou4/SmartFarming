package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Telemetrie.TelemetryDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelemetryDataEntityRepository extends JpaRepository<TelemetryDataEntity, Long> {
    List<TelemetryDataEntity> findByDeviceId(Long deviceId);

    List<TelemetryDataEntity> findByTenantId(Long tenantId);

    List<TelemetryDataEntity> findByDeviceIdAndTenantId(Long deviceId, Long tenantId);
}