package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.DataConnector.DataConnectorEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.DataConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/connectors")
public class DataConnectorController {

    @Autowired
    private DataConnectorService dataConnectorService;

    @PostMapping("/ajouter")
    public DataConnectorEntity ajouterConnector(@RequestParam String protocol,
                                                @RequestParam String endpoint,
                                                @RequestParam String parameters,
                                                @RequestParam Boolean enabled) {
        return dataConnectorService.enregistrerConnector(protocol, endpoint, parameters, enabled);
    }

    @GetMapping("/tous")
    public List<DataConnectorEntity> obtenirConnectors() {
        return dataConnectorService.obtenirTousConnectors();
    }

    @PostMapping("/changerEtat")
    public DataConnectorEntity changerEtat(@RequestParam String protocol, @RequestParam Boolean enabled) {
        return dataConnectorService.changerEtatConnector(protocol, enabled);
    }

    @DeleteMapping("/supprimer/{protocol}")
    public void supprimerConnector(@PathVariable String protocol) {
        dataConnectorService.supprimerConnector(protocol);
    }
}