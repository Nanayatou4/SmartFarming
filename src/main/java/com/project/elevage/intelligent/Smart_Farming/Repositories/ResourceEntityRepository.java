package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceEntityRepository extends JpaRepository<ResourceEntity, Long> {
    List<ResourceEntity> findByTenantId(Long tenantId);
}