package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantAdminEntityRepository extends JpaRepository<TenantAdminEntity, Long> {

    List<TenantAdminEntity> findByTenant_Id(Long tenantId);
}