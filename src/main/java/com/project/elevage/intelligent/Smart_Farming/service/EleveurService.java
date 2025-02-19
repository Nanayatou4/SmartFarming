package com.project.elevage.intelligent.Smart_Farming.service;

import com.project.elevage.intelligent.Smart_Farming.model.Eleveur;
import com.project.elevage.intelligent.Smart_Farming.repository.EleveurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleveurService {

    private final EleveurRepository eleveurRepository;

    public EleveurService(EleveurRepository eleveurRepository) {
        this.eleveurRepository = eleveurRepository;
    }

    public List<Eleveur> getAllEleveurs() {
        return eleveurRepository.findAll();
    }

    public Eleveur getEleveurById(Long id) {
        return eleveurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Éleveur non trouvé"));
    }

    public Eleveur createEleveur(Eleveur eleveur) {
        return eleveurRepository.save(eleveur);
    }
}