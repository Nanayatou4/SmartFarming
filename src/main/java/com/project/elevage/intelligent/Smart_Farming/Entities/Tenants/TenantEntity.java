package com.project.elevage.intelligent.Smart_Farming.Entities.Tenants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.elevage.intelligent.Smart_Farming.Entities.Device.DeviceEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Edge.EdgeEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Resources.TenantResource;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public final class TenantEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "search_text")
    private String searchText;

    @Column(name = "region")
    private String region;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "is_Active")
    private boolean isActive;


    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY)
    private List<UserEntity> utilisateurs;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EdgeEntity> edgeDevices;

    @JsonIgnore
    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TenantResource> tenantResources ;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeviceEntity> devices;

}