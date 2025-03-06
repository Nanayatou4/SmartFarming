package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.Animal.AnimalEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.AnimalRepository;
import com.project.elevage.intelligent.Smart_Farming.Services.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/animals", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AnimalController {

    private final AnimalService animalService;
    private final AnimalRepository animalRepository;
    @GetMapping
    public List<AnimalEntity> getAllAnimals() {
        return animalRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalEntity addAnimal(@RequestBody AnimalEntity animal) {
        return animalService.addAnimal(animal);
    }
}
