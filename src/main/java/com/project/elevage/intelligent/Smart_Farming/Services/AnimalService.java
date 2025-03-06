package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Animal.AnimalEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalEntity addAnimal(AnimalEntity animal) {
        return animalRepository.save(animal);
    }

    public List<AnimalEntity> getAnimauxParEleveur(Long eleveurId) {
        return animalRepository.findByEleveurId(eleveurId);
    }

    public Optional<AnimalEntity> modifierAnimal(Long id, AnimalEntity animalDetails) {
        return animalRepository.findById(id).map(animal -> {
            animal.setNom(animalDetails.getNom());
            animal.setEspece(animalDetails.getEspece());
            animal.setRace(animalDetails.getRace());
            animal.setDateNaissance(animalDetails.getDateNaissance());
            return animalRepository.save(animal);
        });
    }

    public void supprimerAnimal(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new IllegalArgumentException("Animal avec l'ID " + id + " non trouvé.");
        }
        animalRepository.deleteById(id);
    }
}
