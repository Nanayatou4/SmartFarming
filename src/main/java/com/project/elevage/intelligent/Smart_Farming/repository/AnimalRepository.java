package com.project.elevage.intelligent.Smart_Farming.repository;

import com.project.elevage.intelligent.Smart_Farming.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByEleveurId(Long eleveurId);
}
