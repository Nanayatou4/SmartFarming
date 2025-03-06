package com.project.elevage.intelligent.Smart_Farming.Entities.Tenants;

import com.project.elevage.intelligent.Smart_Farming.Entities.Edge.EdgeDeviceEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin.TenantAdminEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "tenant")
    private List<UserEntity> utilisateurs;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EdgeDeviceEntity> edgeDevices = new ArrayList<>();

    // Ajout des getters et setters manuels pour éviter les erreurs de compilation
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
