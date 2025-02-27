package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.ResourceUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceUsageRepository extends JpaRepository<ResourceUsage, Long> {

    List<ResourceUsage> findByTenantId(Long tenantId);
}