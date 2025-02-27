package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Client.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, Long> {
}