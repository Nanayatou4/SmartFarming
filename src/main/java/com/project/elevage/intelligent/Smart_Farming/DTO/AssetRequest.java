package com.project.elevage.intelligent.Smart_Farming.DTO;

import com.project.elevage.intelligent.Smart_Farming.Entities.Asset.AssetEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetRequest {
    private String tenantAdminEmail;
    private Long tenantId;
    private AssetEntity asset;

}
