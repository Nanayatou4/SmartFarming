package com.project.elevage.intelligent.Smart_Farming.Entities.SystemStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "system_status_entity")
public class SystemStatusEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String deviceName; // Nom du dispositif IoT

  @Column(nullable = false)
  private String status; // État du dispositif (OK, ALERTE, CRITIQUE, etc.)

  @Column(nullable = false)
  private double batteryLevel; // Niveau de batterie (%)

  @Column(nullable = false)
  private double temperature; // Température relevée (°C)

  @Column(nullable = false)
  private double humidity; // Humidité relevée (%)

  @Column(nullable = false)
  private LocalDateTime timestamp; // Horodatage de l'enregistrement

  @Column(name = "tenant_id")
  private Long tenantId;

  public SystemStatusEntity(String deviceName, String status, double batteryLevel, double temperature, double humidity, LocalDateTime timestamp, Long tenantId) {
    this.deviceName = deviceName;
    this.status = status;
    this.batteryLevel = batteryLevel;
    this.temperature = temperature;
    this.humidity = humidity;
    this.timestamp = timestamp;
    this.tenantId = tenantId;
  }
}