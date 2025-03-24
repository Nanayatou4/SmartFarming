package com.project.elevage.intelligent.Smart_Farming.Entities.Device;

import com.project.elevage.intelligent.Smart_Farming.Entities.Alarme.AlarmEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Asset.AssetEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Edge.EdgeEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "device")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;  // Par exemple: capteur de température, caméra, etc.
    private int frequency; // Fréquence d'envoi en secondes
    private String deviceToken; // Token unique pour l'authentification dans ThingsBoard


    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlarmEntity> alarmEntityList;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssetEntity> assetEntityList;

    @ManyToOne
    @JoinColumn(name = "edge_id")
    private EdgeEntity edge;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private TenantEntity tenant;



}