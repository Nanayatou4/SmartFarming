package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.DTO.IntegrationDTO;
import com.project.elevage.intelligent.Smart_Farming.Entities.Integration.IntegrationEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins/integrations")
public class IntegrationController {

    @Autowired
    private IntegrationService integrationService;

    // Ajouter ou modifier une intégration
    @PostMapping("/ajouter")
    public IntegrationEntity ajouterIntegration(@RequestBody IntegrationDTO integrationDTO) {
        return integrationService.enregistrerIntegration(
                integrationDTO.getName(),
                integrationDTO.getTenantId(),
                integrationDTO.getType(),
                integrationDTO.getConfiguration(),
                integrationDTO.getEnabled()
        );
    }

    // Activer/Désactiver une intégration
    @PostMapping("/changerEtat")
    public IntegrationEntity changerEtat(@RequestBody IntegrationDTO integrationDTO) {
        return integrationService.changerEtatIntegration(integrationDTO.getName(), integrationDTO.getEnabled());
    }

    // Obtenir la liste des intégrations
    @GetMapping("/all")
    public List<IntegrationEntity> obtenirIntegrations() {
        return integrationService.obtenirToutesIntegrations();
    }

    // Supprimer une intégration
    @DeleteMapping("/supprimer/{name}")
    public void supprimerIntegration(@PathVariable String name) {
        integrationService.supprimerIntegration(name);
    }
}