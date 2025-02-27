package com.project.elevage.intelligent.Smart_Farming.Entities.RFID;


import com.project.elevage.intelligent.Smart_Farming.Entities.Animal.AnimalEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class RFIDTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String tagId; // Code RFID unique

    @OneToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private AnimalEntity animal;



    private LocalDate dateAttribution; // Date à laquelle le tag a été attribué

    private Boolean isLost = false;  // Champs pour indiquer si le tag est perdu

}
