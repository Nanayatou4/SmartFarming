package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Widget.WidgetEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Widget.WidgetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WidgetEntityRepository extends JpaRepository<WidgetEntity, Long> {
    List<WidgetEntity> findByDashboardId(Long dashboardId);

    List<WidgetEntity> findByDashboardIdAndStatus(Long dashboardId, WidgetStatus status);
}