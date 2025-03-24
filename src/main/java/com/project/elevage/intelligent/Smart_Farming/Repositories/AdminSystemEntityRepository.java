package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.AdminSystem.AdminSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminSystemEntityRepository extends JpaRepository<AdminSystemEntity, Long> {
    @Query("SELECT a.email FROM AdminSystemEntity a WHERE a.id = :id")
    String findAdminEmailById(@Param("id") Long id);
}