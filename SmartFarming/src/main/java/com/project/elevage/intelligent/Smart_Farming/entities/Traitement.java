package com.project.elevage.intelligent.Smart_Farming.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Traitement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // Médicament, vaccin, soin
    private String description;
    private String datePrescription;

    @ManyToOne
    @JoinColumn(name = "veterinaire_id")
    private Veterinaire veterinaire;
}
