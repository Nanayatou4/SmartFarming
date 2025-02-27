package com.project.elevage.intelligent.Smart_Farming.Entities;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreation = LocalDateTime.now();

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "last_login_ts")
    private Long lastLoginTs;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "authority")
    private Authority authority;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private TenantEntity tenant;

    //dashboard
}


