package com.project.elevage.intelligent.Smart_Farming.Entities.suiviAlimentation;

import com.project.elevage.intelligent.Smart_Farming.Entities.Animal.AnimalEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Paturage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Double surface;

    @ManyToMany
    @JoinTable(
            name = "paturage_animal",
            joinColumns = @JoinColumn(name = "paturage_id"),
            inverseJoinColumns = @JoinColumn(name = "animal_id")
    )
    private List<AnimalEntity> animaux;

    // Getters et Setters
}

