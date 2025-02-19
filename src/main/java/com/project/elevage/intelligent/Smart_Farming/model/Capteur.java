package com.project.elevage.intelligent.Smart_Farming.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Capteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;  // Température, GPS, RFID...
    private String valeur;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}

