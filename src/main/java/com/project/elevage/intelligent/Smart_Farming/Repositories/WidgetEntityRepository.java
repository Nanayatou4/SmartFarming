package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Widget.WidgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetEntityRepository extends JpaRepository<WidgetEntity, Long> {
}