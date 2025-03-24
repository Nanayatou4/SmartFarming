package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Edge.EdgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdgeDeviceEntityRepository extends JpaRepository<EdgeEntity, Long> {
    List<EdgeEntity> findByTenantId(Long tenantId);

    List<EdgeEntity> findByIsGateway(boolean b);
}