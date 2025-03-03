package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Animal.AnimalEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.AnimalEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalEntityRepository animalRepository;

    public AnimalEntity ajouterAnimal(AnimalEntity animal) {
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
        animalRepository.deleteById(id);
    }
}
