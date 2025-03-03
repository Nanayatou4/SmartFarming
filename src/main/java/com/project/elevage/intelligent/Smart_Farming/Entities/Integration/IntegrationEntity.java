package com.project.elevage.intelligent.Smart_Farming.Entities.Integration;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "integration")
public class IntegrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Nom de l'intégration (AWS, Kafka...)

    @Column(nullable = false)
    private String type; // Type d'intégration (Cloud, Messaging, API...)

    @Column(nullable = false)
    private String configuration; // Configuration JSON de l'intégration

    @Column(nullable = false)
    private Boolean enabled; // Activation/Désactivation
}