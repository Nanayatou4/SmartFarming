package com.project.elevage.intelligent.Smart_Farming.services;

import com.project.elevage.intelligent.Smart_Farming.dao.RapportSanteRepository;
import com.project.elevage.intelligent.Smart_Farming.entities.RapportSante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RapportSanteService {
    @Autowired
    private RapportSanteRepository rapportSanteRepository;

    public List<RapportSante> getAllRapports() {
        return rapportSanteRepository.findAll();
    }

    public RapportSante saveRapport(RapportSante rapport) {
        return rapportSanteRepository.save(rapport);
    }
}

