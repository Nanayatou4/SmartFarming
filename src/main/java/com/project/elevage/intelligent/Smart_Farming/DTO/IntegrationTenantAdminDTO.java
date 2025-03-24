package com.project.elevage.intelligent.Smart_Farming.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IntegrationTenantAdminDTO {
    private Long integrationId;
    private String email;
    private Long tenantId;
    private String name;
    private String type;
    private String configuration;
    private Boolean enabled;
}
