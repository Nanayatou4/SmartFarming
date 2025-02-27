package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.DataConnector.DataConnectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataConnectorEntityRepository extends JpaRepository<DataConnectorEntity, Long> {
    Optional<DataConnectorEntity> findByProtocol(String protocol);
}