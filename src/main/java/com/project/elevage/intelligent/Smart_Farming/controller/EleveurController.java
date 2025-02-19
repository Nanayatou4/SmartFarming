package com.project.elevage.intelligent.Smart_Farming.controller;

import com.project.elevage.intelligent.Smart_Farming.model.Eleveur;
import com.project.elevage.intelligent.Smart_Farming.service.EleveurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/eleveurs")
public class EleveurController {

    private final EleveurService eleveurService;

    public EleveurController(EleveurService eleveurService) {
        this.eleveurService = eleveurService;
    }

    @GetMapping
    public List<Eleveur> getAllEleveurs() {
        return eleveurService.getAllEleveurs();
    }

    @GetMapping("/{id}")
    public Eleveur getEleveurById(@PathVariable Long id) {
        return eleveurService.getEleveurById(id);
    }

    @PostMapping
    public Eleveur createEleveur(@RequestBody Eleveur eleveur) {
        return eleveurService.createEleveur(eleveur);
    }
}

