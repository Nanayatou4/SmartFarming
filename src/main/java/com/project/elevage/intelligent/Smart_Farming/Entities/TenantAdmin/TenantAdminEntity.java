package com.project.elevage.intelligent.Smart_Farming.Entities.TenantAdmin;

import com.project.elevage.intelligent.Smart_Farming.Entities.Authority;
import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TenantAdminEntity extends UserEntity {

    public TenantAdminEntity() {
        this.setAuthority(Authority.TENANT_ADMIN);
    }

}