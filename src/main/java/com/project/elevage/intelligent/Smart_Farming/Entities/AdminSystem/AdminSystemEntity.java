package com.project.elevage.intelligent.Smart_Farming.Entities.AdminSystem;

import com.project.elevage.intelligent.Smart_Farming.Entities.Authority;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import jakarta.persistence.Entity;

@Entity
public class AdminSystemEntity extends UserEntity {

    public AdminSystemEntity() {
        this.setAuthority(Authority.ADMIN_SYSTEM);
    }
}
