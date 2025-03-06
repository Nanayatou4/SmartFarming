
package com.project.elevage.intelligent.Smart_Farming.Entities.suiviAlimentation;

import com.project.elevage.intelligent.Smart_Farming.Entities.Animal.AnimalEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alimentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeAliment;
    private Double quantite;
    private LocalDate dateAlimentation;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private AnimalEntity animal;

    // Getters et Setters
}
