package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.suiviAlimentation.Alimentation;
import com.project.elevage.intelligent.Smart_Farming.Services.AlimentationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/alimentations", produces = "application/json")
@CrossOrigin(origins = "*") // Autorise les requêtes cross-origin
@AllArgsConstructor
public class AlimentationController {

    private final AlimentationService alimentationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alimentation addAlimentation(@RequestBody Alimentation alimentation) {
        return alimentationService.addAlimentation(alimentation);
    }

    @GetMapping("/animal/{animalId}")
    public List<Alimentation> getAlimentationsByAnimal(@PathVariable Long animalId) {
        return alimentationService.getAlimentationsByAnimal(animalId);
    }
}
