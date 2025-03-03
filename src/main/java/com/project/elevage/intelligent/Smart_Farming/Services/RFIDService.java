package com.project.elevage.intelligent.Smart_Farming.Services;


import com.project.elevage.intelligent.Smart_Farming.Entities.Animal.AnimalEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.RFID.RFIDTagEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.AnimalEntityRepository;
import com.project.elevage.intelligent.Smart_Farming.Repositories.RFIDTagEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RFIDService {


    @Autowired
    private RFIDTagEntityRepository rfidTagRepository;

    @Autowired
    private AnimalEntityRepository animalRepository;

    @Autowired
    private NotificationService  notificationService;

    /**
     * Vérifier si le tag RFID existe déjà et renvoie l'animal associé si trouvé.
     */
    public Optional<AnimalEntity> verifierTagRfid(String tagId) {
        Optional<RFIDTagEntity> rfidTagEntity = rfidTagRepository.findByTagId(tagId);
        if (rfidTagEntity.isEmpty()) {
            // Tag non trouvé, retournez une valeur ou gérez l'erreur
            return Optional.empty();
        }
        return rfidTagEntity.map(RFIDTagEntity::getAnimal);
    }


    /**
     * Associer un nouveau tag RFID à un animal (et conserve l'historique)
     */
    @Transactional
    public RFIDTagEntity associerTagAAnimal(Long animalId, String tagId) {
        AnimalEntity animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal non trouvé"));

        RFIDTagEntity newTag = new RFIDTagEntity();
        newTag.setTagId(tagId);
        newTag.setAnimal(animal);
        newTag.setDateAttribution(LocalDate.now());

        // Associer le tag à l'animal
        animal.setRfidTag(newTag);
        animalRepository.save(animal);  // Sauvegarder l'animal avec le nouveau tag

        return rfidTagRepository.save(newTag);  // Sauvegarder le tag
    }

    /**
     * Remplacer le tag RFID existant si l'animal perd son tag.
     */
    @Transactional
    public RFIDTagEntity assignNewTagToAnimal(Long animalId, String newTagId) {
        // Récupérer l'animal par ID
        AnimalEntity animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal non trouvé"));

        // Si l'animal a déjà un tag, le marquer comme perdu
        RFIDTagEntity currentTag = animal.getRfidTag();
        if (currentTag != null) {
            currentTag.setIsLost(true);
            rfidTagRepository.save(currentTag);  // Mettre à jour l'ancien tag comme perdu
        }

        // Créer et attribuer un nouveau tag
        RFIDTagEntity newTag = new RFIDTagEntity();
        newTag.setTagId(newTagId);  // Nouveau code RFID
        newTag.setAnimal(animal);  // Associer le tag à l'animal
        newTag.setDateAttribution(LocalDate.now());

        // Sauvegarder le nouveau tag et l'animal avec ce tag
        animal.setRfidTag(newTag);
        animalRepository.save(animal);
        return rfidTagRepository.save(newTag);
    }

    public RFIDTagEntity retrouverTag(Long tagId) {
        RFIDTagEntity tag = rfidTagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag RFID non trouvé"));
        tag.setIsLost(false); // Tag retrouvé
        return rfidTagRepository.save(tag);
    }

    public String traiterTag(String tagId) { // Renommer le paramètre
        Optional<RFIDTagEntity> tagEntityOpt = rfidTagRepository.findByTagId(tagId); // Corriger l'appel au repository

        if (tagEntityOpt.isEmpty()) {
            notificationService.envoyerAlerte("Tag inconnu détecté : " + tagId);
            return "Alerte : Tag inconnu !";
        }

        RFIDTagEntity tagEntity = tagEntityOpt.get();

        if (tagEntity.getIsLost()) {
            notificationService.envoyerAlerte("Tag perdu retrouvé : " + tagId);
            tagEntity.setIsLost(false);
            rfidTagRepository.save(tagEntity);
            return "Tag retrouvé et mis à jour.";
        }

        return "Tag valide détecté : " + tagEntity.getAnimal().getNom();
    }



}
