package com.project.elevage.intelligent.Smart_Farming.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelemetryRequest {
    private String email;
    private Long tenantId;
    private Long deviceId;
}
