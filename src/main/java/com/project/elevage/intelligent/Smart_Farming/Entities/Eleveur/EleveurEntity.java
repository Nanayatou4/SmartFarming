package com.project.elevage.intelligent.Smart_Farming.Entities.Eleveur;

import com.project.elevage.intelligent.Smart_Farming.Entities.Animal.AnimalEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Authority;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class EleveurEntity extends UserEntity {

    @Column(nullable = false)
    private String specialite;

    @Column(nullable = false)
    private String experience;

    private int nombre_animaux;

    @Column(nullable = false)
    private String region;

    @OneToMany(mappedBy = "eleveur", cascade = CascadeType.ALL)
    private List<AnimalEntity> animaux ;


    public EleveurEntity() {
        this.setAuthority(Authority.ELEVEUR);
    }

}