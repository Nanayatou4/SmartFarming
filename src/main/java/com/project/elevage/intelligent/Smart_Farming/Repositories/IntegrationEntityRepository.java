package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Integration.IntegrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntegrationEntityRepository extends JpaRepository<IntegrationEntity, Long> {
  Optional<IntegrationEntity> findByName(String name);
}