package com.project.elevage.intelligent.Smart_Farming.Entities.DataConnector;

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
@Table(name = "data_connector")
public class DataConnectorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String protocol; // REST, MQTT, OPC-UA...

    @Column(nullable = false)
    private String endpoint; // URL du serveur ou broker

    @Column(nullable = false)
    private String parameters; // JSON des paramètres de connexion

    @Column(nullable = false)
    private Boolean enabled;
}