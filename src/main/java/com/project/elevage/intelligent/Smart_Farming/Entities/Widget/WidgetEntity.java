package com.project.elevage.intelligent.Smart_Farming.Entities.Widget;

import com.project.elevage.intelligent.Smart_Farming.Entities.Dashboard.DashboardEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "widget_entity")
public class WidgetEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;  // Titre du widget
  private String type;   // Type de widget (par exemple : 'chart', 'gauge', etc.)
  private String config; // Configuration du widget (JSON ou autre)
  @Enumerated(EnumType.STRING)
  private WidgetStatus status = WidgetStatus.ACTIVE; // Par défaut actif

  @ManyToOne
  private DashboardEntity dashboard;  // Le dashboard auquel appartient ce widget
}