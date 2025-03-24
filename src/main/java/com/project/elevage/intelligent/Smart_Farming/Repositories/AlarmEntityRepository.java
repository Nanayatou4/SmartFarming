package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Alarme.AlarmEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.Alarme.AlarmStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmEntityRepository extends JpaRepository<AlarmEntity, Long> {
    List<AlarmEntity> findByDeviceId(Long deviceId);
    List<AlarmEntity> findByStatus(AlarmStatus status);
}