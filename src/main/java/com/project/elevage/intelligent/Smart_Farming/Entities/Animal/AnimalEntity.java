package com.project.elevage.intelligent.Smart_Farming.Entities.Animal;

import com.project.elevage.intelligent.Smart_Farming.Entities.Eleveur.EleveurEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.RFID.RFIDTagEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "animal")
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String espece;
    private String race;
    private LocalDate dateNaissance;
    private int age;
    private double poids;

    @ManyToOne
    @JoinColumn(name = "eleveur_id", nullable = false)
    private EleveurEntity eleveur;

    @OneToOne(mappedBy = "animal", cascade = CascadeType.ALL)
    private RFIDTagEntity rfidTag;

}