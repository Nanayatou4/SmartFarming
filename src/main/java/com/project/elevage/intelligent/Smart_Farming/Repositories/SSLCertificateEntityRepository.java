package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.SSL.SSLCertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SSLCertificateEntityRepository extends JpaRepository<SSLCertificateEntity, Long> {
  Optional<SSLCertificateEntity> findByDomain(String domain);
}