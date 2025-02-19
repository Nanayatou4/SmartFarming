package com.project.elevage.intelligent.Smart_Farming.services;

import com.project.elevage.intelligent.Smart_Farming.dao.TraitementRepository;
import com.project.elevage.intelligent.Smart_Farming.entities.Traitement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraitementService {
    @Autowired
    private TraitementRepository traitementRepository;

    public List<Traitement> getAllTraitements() {
        return traitementRepository.findAll();
    }

    public Traitement saveTraitement(Traitement traitement) {
        return traitementRepository.save(traitement);
    }
}
