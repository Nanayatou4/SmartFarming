package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.suiviAlimentation.Alimentation;
import com.project.elevage.intelligent.Smart_Farming.Repositories.AlimentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlimentationService {
    @Autowired
    private AlimentationRepository alimentationRepository;

    public Alimentation addAlimentation(Alimentation alimentation) {
        return alimentationRepository.save(alimentation);
    }

    public List<Alimentation> getAlimentationsByAnimal(Long animalId) {
        return alimentationRepository.findByAnimalId(animalId); // Correction : Ajout du paramètre animalId
    }
}
