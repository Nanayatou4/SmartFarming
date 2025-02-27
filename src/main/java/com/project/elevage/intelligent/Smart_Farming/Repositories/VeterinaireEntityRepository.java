package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Veterinaire.VeterinaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeterinaireEntityRepository extends JpaRepository<VeterinaireEntity, Long> {
    List<VeterinaireEntity> findByTenant(TenantEntity tenant);
}