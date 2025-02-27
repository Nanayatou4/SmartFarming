package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.Integration.IntegrationEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/integrations")
public class IntegrationController {

    @Autowired
    private IntegrationService integrationService;

    // Ajouter ou modifier une intégration
    @PostMapping("/ajouter")
    public IntegrationEntity ajouterIntegration(@RequestParam String name,
                                                @RequestParam String type,
                                                @RequestParam String configuration,
                                                @RequestParam Boolean enabled) {
        return integrationService.enregistrerIntegration(name, type, configuration, enabled);
    }

    // Activer/Désactiver une intégration
    @PostMapping("/changerEtat")
    public IntegrationEntity changerEtat(@RequestParam String name, @RequestParam Boolean enabled) {
        return integrationService.changerEtatIntegration(name, enabled);
    }

    // Obtenir la liste des intégrations
    @GetMapping("/toutes")
    public List<IntegrationEntity> obtenirIntegrations() {
        return integrationService.obtenirToutesIntegrations();
    }

    // Supprimer une intégration
    @DeleteMapping("/supprimer/{name}")
    public void supprimerIntegration(@PathVariable String name) {
        integrationService.supprimerIntegration(name);
    }
}