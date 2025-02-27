package com.project.elevage.intelligent.Smart_Farming.Entities.Alarme;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.elevage.intelligent.Smart_Farming.Entities.Device.DeviceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "alarm")
public class AlarmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // Type d'alarme (ex : "Température Haute", "Humidité Basse", etc.)
    private String severity; // Gravité : "CRITIQUE", "MAJEURE", "MINEURE"

    @Enumerated(EnumType.STRING)
    private AlarmStatus status;
    // "ACTIVE", "RÉSOLUE"

    private double threshold; // Valeur seuil
    private double actualValue; // Valeur actuelle du capteur
    private LocalDateTime timestamp; // Date et heure de l'alarme

    @ManyToOne
    @JoinColumn(name = "device_id")
    @JsonIgnore
    private DeviceEntity device;
    // L'appareil qui a déclenché l'alarme
}