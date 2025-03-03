package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Integration.IntegrationEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.IntegrationEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntegrationService {
    @Autowired
    private IntegrationEntityRepository integrationRepository;

    // Ajouter ou mettre à jour une intégration
    public IntegrationEntity enregistrerIntegration(String name, String type, String configuration, Boolean enabled) {
        Optional<IntegrationEntity> integrationOpt = integrationRepository.findByName(name);
        IntegrationEntity integration = integrationOpt.orElse(new IntegrationEntity());
        integration.setName(name);
        integration.setType(type);
        integration.setConfiguration(configuration);
        integration.setEnabled(enabled);
        return integrationRepository.save(integration);
    }

    // Activer/Désactiver une intégration
    public IntegrationEntity changerEtatIntegration(String name, Boolean enabled) {
        IntegrationEntity integration = integrationRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Intégration non trouvée"));
        integration.setEnabled(enabled);
        return integrationRepository.save(integration);
    }

    // Obtenir la liste des intégrations
    public List<IntegrationEntity> obtenirToutesIntegrations() {
        return integrationRepository.findAll();
    }

    // Supprimer une intégration
    public void supprimerIntegration(String name) {
        IntegrationEntity integration = integrationRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Intégration non trouvée"));
        integrationRepository.delete(integration);
    }
}
