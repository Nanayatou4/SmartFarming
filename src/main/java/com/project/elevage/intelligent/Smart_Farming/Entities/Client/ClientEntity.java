package com.project.elevage.intelligent.Smart_Farming.Entities.Client;

import com.project.elevage.intelligent.Smart_Farming.Entities.Authority;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ClientEntity extends UserEntity {

    public ClientEntity() {
        this.setAuthority(Authority.CLIENT);
    }
}