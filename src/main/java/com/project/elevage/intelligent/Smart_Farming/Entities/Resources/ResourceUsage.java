package com.project.elevage.intelligent.Smart_Farming.Entities.Resources;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "resource_usage")
public class ResourceUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    @JsonIgnore
    private TenantEntity tenant;

    private double cpuUsage;  // En pourcentage
    private double memoryUsage;  // En Mo ou Go
    private double storageUsage;  // En Go
    private double bandwidthUsage;  // En Mbps
    private LocalDateTime lastUpdated;

}
