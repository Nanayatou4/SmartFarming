package com.project.elevage.intelligent.Smart_Farming.dao;

import com.project.elevage.intelligent.Smart_Farming.entities.RapportSante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RapportSanteRepository extends JpaRepository<RapportSante, Long> {
}
