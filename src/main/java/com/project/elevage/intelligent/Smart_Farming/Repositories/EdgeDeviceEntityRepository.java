package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Edge.EdgeDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdgeDeviceEntityRepository extends JpaRepository<EdgeDeviceEntity, Long> {
    List<EdgeDeviceEntity> findByTenantId(Long tenantId);

    List<EdgeDeviceEntity> findByIsGateway(boolean b);
}