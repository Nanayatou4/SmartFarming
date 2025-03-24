package com.project.elevage.intelligent.Smart_Farming.Entities.Asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.elevage.intelligent.Smart_Farming.Entities.Device.DeviceEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asset")
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // Nom de l'objet
    private String description;  // Description de l'objet

    @ManyToOne
    @JoinColumn(name = "device_id")
    @JsonIgnore
    private DeviceEntity device;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private TenantEntity tenant;


}