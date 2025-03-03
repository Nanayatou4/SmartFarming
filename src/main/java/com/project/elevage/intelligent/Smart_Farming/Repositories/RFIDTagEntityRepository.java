package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.RFID.RFIDTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RFIDTagEntityRepository extends JpaRepository<RFIDTagEntity, Long> {
    Optional<RFIDTagEntity> findByTagId(String tagId);
}