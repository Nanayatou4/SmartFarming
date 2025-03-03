package com.project.elevage.intelligent.Smart_Farming.Entities.Resources;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "resources")
public class ResourceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String type; // ex: "STORAGE", "DATABASE", "DEVICE"
  private String description;
  private Integer limitValue; // ex: 100 GB, 50 appareils connectés
  private Boolean isActive;

  @ManyToOne
  @JoinColumn(name = "tenant_id", nullable = false)
  private TenantEntity tenant;


}