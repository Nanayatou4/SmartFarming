package com.project.elevage.intelligent.Smart_Farming.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rule_engine_script")
public class RuleEngineScriptEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Lob
  @Column(nullable = false)
  private String script; // Contenu du script JavaScript

  @Column(nullable = false)
  private Boolean enabled;
}