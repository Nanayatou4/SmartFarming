package com.project.elevage.intelligent.Smart_Farming.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eleveur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prenom;
    private String nom;
    private String email;
    private String telephone;

    @OneToMany(mappedBy = "eleveur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animal> animaux;
}

