package com.project.elevage.intelligent.Smart_Farming.Entities.SSL;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ssl_certificate")
public class SSLCertificateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String domain;

    @Lob
    @Column(nullable = false)
    private String certificate; // Contenu du certificat

    @Lob
    @Column(nullable = false)
    private String privateKey; // Clé privée
}