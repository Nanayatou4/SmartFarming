package com.project.elevage.intelligent.Smart_Farming.Entities.Dashboard;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Widget.WidgetEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "dashboard")
public class DashboardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // Nom du tableau de bord
    private String description;  // Description du tableau de bord

    @OneToMany(mappedBy = "dashboard")
    private List<WidgetEntity> widgets;  // Liste des widgets dans le tableau de bord

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private TenantEntity tenant;

}