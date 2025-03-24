package com.project.elevage.intelligent.Smart_Farming.Entities.Edge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.elevage.intelligent.Smart_Farming.Entities.Device.DeviceEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "edge_device")
public class EdgeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name; // Nom du dispositif
  private String type; // Type de dispositif (Capteur, Passerelle, etc.)
  private String ipAddress; // Adresse IP pour la communication
  private boolean isConnected; // État de connexion
  private LocalDateTime lastSync; // Dernière synchronisation avec le serveur
  private boolean isGateway; // Indique si c'est une passerelle
  private String edgeToken; // Token pour synchronisation avec ThingsBoard



  @ManyToOne
  @JoinColumn(name = "tenant_id", nullable = false)
  @JsonIgnore
  private TenantEntity tenant; // Locataire associé au dispositif

  @OneToMany(mappedBy = "edgeDevice", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DeviceEntity> devices; // Appareils connectés à cette passerelle

}