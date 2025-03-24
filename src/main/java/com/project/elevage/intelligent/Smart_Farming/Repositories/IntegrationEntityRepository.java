package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Integration.IntegrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IntegrationEntityRepository extends JpaRepository<IntegrationEntity, Long> {
  Optional<IntegrationEntity> findByName(String name);

    List<IntegrationEntity> findByTenantId(Long tenantId);

  Optional<IntegrationEntity> findByIdAndTenantId(Long integrationId, Long tenantId);

}