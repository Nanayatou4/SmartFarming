package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.RuleEngineScriptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuleEngineScriptEntityRepository extends JpaRepository<RuleEngineScriptEntity, Long> {
  Optional<RuleEngineScriptEntity> findByName(String name);
}