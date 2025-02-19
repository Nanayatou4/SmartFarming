package com.project.elevage.intelligent.Smart_Farming.controlers;

import com.project.elevage.intelligent.Smart_Farming.entities.RapportSante;
import com.project.elevage.intelligent.Smart_Farming.entities.Traitement;
import com.project.elevage.intelligent.Smart_Farming.entities.Veterinaire;
import com.project.elevage.intelligent.Smart_Farming.services.RapportSanteService;
import com.project.elevage.intelligent.Smart_Farming.services.ServiceVeterinaire;
import com.project.elevage.intelligent.Smart_Farming.services.ThingsBoardService;
import com.project.elevage.intelligent.Smart_Farming.services.TraitementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinaires")
public class VeterinaireController {
    @Autowired
    private ServiceVeterinaire serviceVeterinaire;

    @Autowired
    private TraitementService traitementService;

    @Autowired
    private RapportSanteService rapportSanteService;

    @Autowired
    private ThingsBoardService thingsBoardService;

    @GetMapping
    public List<Veterinaire> getAllVeterinaires() {
        return serviceVeterinaire.getAllVeterinaires();
    }

    @GetMapping("/{id}")
    public Veterinaire getVeterinaireById(@PathVariable Long id) {
        return serviceVeterinaire.getVeterinaireById(id);
    }

    @PostMapping
    public Veterinaire createVeterinaire(@RequestBody Veterinaire veterinaire) {
        return serviceVeterinaire.saveVeterinaire(veterinaire);
    }

    @DeleteMapping("/{id}")
    public void deleteVeterinaire(@PathVariable Long id) {
        serviceVeterinaire.deleteVeterinaire(id);
    }

//     une route permettant au vétérinaire d’accéder aux données des animaux.
    @GetMapping("/suivi/{deviceId}")
    public String getAnimalHealthData(@PathVariable String deviceId) {
        return thingsBoardService.getAnimalData(deviceId);
    }

    @PostMapping("/traitements")
    public Traitement prescrireTraitement(@RequestBody Traitement traitement) {
        return traitementService.saveTraitement(traitement);
    }

    @PostMapping("/rapports")
    public RapportSante genererRapport(@RequestBody RapportSante rapport) {
        return rapportSanteService.saveRapport(rapport);
    }

}
