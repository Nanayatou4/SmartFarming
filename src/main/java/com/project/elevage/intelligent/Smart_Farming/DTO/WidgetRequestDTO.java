package com.project.elevage.intelligent.Smart_Farming.DTO;

import com.project.elevage.intelligent.Smart_Farming.Entities.Widget.WidgetEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WidgetRequestDTO {
    private Long tenantAdminId;
    private WidgetEntity widget;
}

