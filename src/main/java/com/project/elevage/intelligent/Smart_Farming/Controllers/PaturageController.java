package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.suiviAlimentation.Paturage;
import com.project.elevage.intelligent.Smart_Farming.Services.PaturageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/paturages", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PaturageController {

    private final PaturageService paturageService;

    @GetMapping
    public List<Paturage> getAllPaturages() {
        return paturageService.getAllPaturages();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paturage addPaturage(@RequestBody Paturage paturage) {
        return paturageService.addPaturage(paturage);
    }
}
