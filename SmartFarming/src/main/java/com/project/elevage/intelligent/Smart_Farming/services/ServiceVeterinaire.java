package com.project.elevage.intelligent.Smart_Farming.services;

import com.project.elevage.intelligent.Smart_Farming.dao.VeterinaireRepository;
import com.project.elevage.intelligent.Smart_Farming.entities.Veterinaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceVeterinaire {
    @Autowired
    private VeterinaireRepository veterinaireRepository;

    public List<Veterinaire> getAllVeterinaires() {
        return veterinaireRepository.findAll();
    }

    public Veterinaire getVeterinaireById(Long id) {
        return veterinaireRepository.findById(id).orElse(null);
    }

    public Veterinaire saveVeterinaire(Veterinaire veterinaire) {
        return veterinaireRepository.save(veterinaire);
    }

    public void deleteVeterinaire(Long id) {
        veterinaireRepository.deleteById(id);
    }
}
