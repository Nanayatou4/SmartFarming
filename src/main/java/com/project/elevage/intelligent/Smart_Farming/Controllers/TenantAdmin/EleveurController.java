package com.project.elevage.intelligent.Smart_Farming.Controllers.TenantAdmin;


import com.project.elevage.intelligent.Smart_Farming.Entities.Animal.AnimalEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eleveurs")
public class EleveurController {

    @Autowired
    private AnimalService animalService;

    @PostMapping("/ajouter-animal")
    public ResponseEntity<AnimalEntity> ajouterAnimal(@RequestBody AnimalEntity animal) {
        return ResponseEntity.ok(animalService.ajouterAnimal(animal));
    }

    @GetMapping("/liste-animaux/{eleveurId}")
    public ResponseEntity<List<AnimalEntity>> getAnimauxParEleveur(@PathVariable Long eleveurId) {
        return ResponseEntity.ok(animalService.getAnimauxParEleveur(eleveurId));
    }

    @PutMapping("/modifier-animal/{id}")
    public ResponseEntity<AnimalEntity> modifierAnimal(@PathVariable Long id, @RequestBody AnimalEntity animalDetails) {
        Optional<AnimalEntity> updatedAnimal = animalService.modifierAnimal(id, animalDetails);
        return updatedAnimal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer-animal/{id}")
    public ResponseEntity<Void> supprimerAnimal(@PathVariable Long id) {
        animalService.supprimerAnimal(id);
        return ResponseEntity.noContent().build();
    }
}
