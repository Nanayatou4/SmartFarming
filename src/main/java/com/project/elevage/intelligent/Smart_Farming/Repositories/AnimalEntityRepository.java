package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Animal.AnimalEntity;
import com.project.elevage.intelligent.Smart_Farming.Entities.RFID.RFIDTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalEntityRepository extends JpaRepository<AnimalEntity, Long> {
  List<AnimalEntity> findByEleveurId(Long eleveurId);

  @Query("SELECT a FROM AnimalEntity a WHERE a.rfidTag.tagId = :rfidTag")
  Optional<AnimalEntity> findByRfidTag(@Param("rfidTag") String rfidTag);
}