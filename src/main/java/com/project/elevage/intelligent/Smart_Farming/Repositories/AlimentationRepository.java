package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.suiviAlimentation.Alimentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentationRepository extends JpaRepository<Alimentation, Long> {
    List<Alimentation> findByAnimalId(Long animalId); // Correction : Ajout du paramètre animalId
}
