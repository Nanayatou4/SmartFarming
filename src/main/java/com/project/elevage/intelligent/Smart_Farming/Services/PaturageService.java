package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.suiviAlimentation.Paturage;
import com.project.elevage.intelligent.Smart_Farming.Repositories.PaturageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaturageService {
    @Autowired
    private PaturageRepository paturageRepository;

    public List<Paturage> getAllPaturages() {
        return paturageRepository.findAll();
    }

    public Paturage addPaturage(Paturage paturage) {
        return (Paturage) paturageRepository.save(paturage);
    }
}
