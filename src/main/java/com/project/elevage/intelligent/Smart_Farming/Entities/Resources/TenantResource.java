package com.project.elevage.intelligent.Smart_Farming.Entities.Resources;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tenant_resources")
public class TenantResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private TenantEntity tenant;

    private Long storageQuota; // Stockage alloué (en MB)
    private Long usedStorage;  // Stockage utilisé (en MB)

    private Integer maxDevices; // Nombre max d’appareils connectés

}
