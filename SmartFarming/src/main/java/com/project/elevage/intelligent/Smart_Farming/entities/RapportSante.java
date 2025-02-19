package com.project.elevage.intelligent.Smart_Farming.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RapportSante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String details;
    private String dateRapport;

    @ManyToOne
    @JoinColumn(name = "veterinaire_id")
    private Veterinaire veterinaire;
}
