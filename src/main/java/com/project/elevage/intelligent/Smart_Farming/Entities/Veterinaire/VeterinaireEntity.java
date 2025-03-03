package com.project.elevage.intelligent.Smart_Farming.Entities.Veterinaire;

import com.project.elevage.intelligent.Smart_Farming.Entities.Authority;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class VeterinaireEntity extends UserEntity {
    private String diplome;
    private String clinique;

    public VeterinaireEntity() {
        this.setAuthority(Authority.VETERINAIRE);
    }
}