package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.Animal.AnimalEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.RFID.RFIDTagEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.RFIDService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rfid")
public class RFIDController {

    private final RFIDService rfidService;

    public RFIDController(RFIDService rfidService) {
        this.rfidService = rfidService;
    }

    /**
     * Vérifie si un tag RFID est associé à un animal.
     */
    @GetMapping("/verifier/{tag}")
    public ResponseEntity<?> verifierTagRfid(@PathVariable String tag) {
        Optional<AnimalEntity> animal = rfidService.verifierTagRfid(tag);
        return animal.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Associe un tag RFID à un animal.
     */
    @PostMapping("/associer")
    public ResponseEntity<RFIDTagEntity> associerTagAAnimal(@RequestParam Long animalId,
                                                            @RequestParam String tag) {
        RFIDTagEntity newTag = rfidService.associerTagAAnimal(animalId, tag);
        return ResponseEntity.ok(newTag);
    }

    /**
     * Remplace un tag RFID (ancien perdu, nouveau attribué).
     */
    @PutMapping("/remplacer")
    public ResponseEntity<RFIDTagEntity> remplacerTag(@RequestParam Long animalId,
                                                      @RequestParam String newTag) {
        RFIDTagEntity updatedTag = rfidService.assignNewTagToAnimal(animalId, newTag);
        return ResponseEntity.ok(updatedTag);
    }

    /**
     * Retrouve un tag RFID perdu et le réactive.
     */
    @PutMapping("/retrouver/{id}")
    public ResponseEntity<RFIDTagEntity> retrouverTag(@PathVariable Long id) {
        RFIDTagEntity tag = rfidService.retrouverTag(id);
        return ResponseEntity.ok(tag);
    }

    @PostMapping
    public ResponseEntity<String> recevoirTag(@RequestBody RFIDTagRequest request) {
        String message = rfidService.traiterTag(request.getTag());
        return ResponseEntity.ok(message);
    }

    // DTO pour recevoir les requêtes JSON
    @Getter
    @Setter
    static
    class RFIDTagRequest {
        private String tag;
    }


}
