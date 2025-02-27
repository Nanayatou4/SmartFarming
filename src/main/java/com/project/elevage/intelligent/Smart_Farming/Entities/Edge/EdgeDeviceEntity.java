package com.project.elevage.intelligent.Smart_Farming.Entities.Edge;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "edge_device")
public class EdgeDeviceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name; // Nom du dispositif
  private String type; // Type de dispositif (Capteur, Passerelle, etc.)
  private String ipAddress; // Adresse IP pour la communication
  private boolean isConnected; // État de connexion
  private LocalDateTime lastSync; // Dernière synchronisation avec le serveur
  private boolean isGateway; // Indique si c'est une passerelle


  @ManyToOne
  @JoinColumn(name = "tenant_id", nullable = false)
  private TenantEntity tenant; // Locataire associé au dispositif
}