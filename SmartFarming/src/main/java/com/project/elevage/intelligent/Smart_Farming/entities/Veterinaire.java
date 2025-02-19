package com.project.elevage.intelligent.Smart_Farming.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Veterinaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String telephone;

    @OneToMany(mappedBy = "veterinaire", cascade = CascadeType.ALL)
    private List<Traitement> traitements;
}
