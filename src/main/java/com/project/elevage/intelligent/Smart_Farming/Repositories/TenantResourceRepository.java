package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.TenantResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantResourceRepository extends JpaRepository<TenantResource, Long> {
    TenantResource findByTenantId(Long tenantId);
}