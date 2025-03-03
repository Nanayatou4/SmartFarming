package com.project.elevage.intelligent.Smart_Farming.Entities.Plugin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plugin")
public class PluginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique

    @Column(nullable = false, unique = true)
    private String name; // Nom du plugin

    @Column(nullable = false)
    private String description; // Description du plugin

    @Column(nullable = false)
    private String version; // Version du plugin

    @Column(columnDefinition = "TEXT")
    private String configuration; // Configuration JSON du plugin

    @Column(nullable = false)
    private boolean activated = false; // Statut du plugin

}