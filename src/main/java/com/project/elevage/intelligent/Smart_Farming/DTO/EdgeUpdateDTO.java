package com.project.elevage.intelligent.Smart_Farming.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EdgeUpdateDTO {
        private String name;
        private String type;
        private String ipAddress;
        private Boolean isConnected;
        private Boolean isGateway;
}
