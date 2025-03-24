package com.project.elevage.intelligent.Smart_Farming.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntegrationDTO {
        private String name;
        private Long tenantId;
        private String type;
        private String configuration;
        private Boolean enabled;

}
