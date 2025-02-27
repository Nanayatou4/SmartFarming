package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Eleveur.EleveurEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EleveurEntityRepository extends JpaRepository<EleveurEntity, Long> {
    List<EleveurEntity> findByTenant(TenantEntity tenant);
}