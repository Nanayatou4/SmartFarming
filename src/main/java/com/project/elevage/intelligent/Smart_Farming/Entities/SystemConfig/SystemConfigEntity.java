package com.project.elevage.intelligent.Smart_Farming.Entities.SystemConfig;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "system_config")
public class SystemConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String key; // Exemple: "mqtt_broker_url", "smtp_server"

    @Column(nullable = false)
    private String value; // Valeur de configuration
}