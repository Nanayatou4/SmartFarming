package com.project.elevage.intelligent.Smart_Farming.Entities.Telemetrie;

import com.project.elevage.intelligent.Smart_Farming.Entities.Device.DeviceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "telemetry_data")
public class TelemetryDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sensorType; // Type de capteur : "Température", "Humidité", etc.
    private double value; // Valeur mesurée
    private LocalDateTime timestamp; // Date et heure de la mesure

    @ManyToOne
    private DeviceEntity device; // L'appareil qui a envoyé la donnée

    @Column(name = "tenant_id")
    private Long tenantId;
}