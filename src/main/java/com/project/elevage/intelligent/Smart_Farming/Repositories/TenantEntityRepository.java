package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Tenants.TenantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TenantEntityRepository extends JpaRepository<TenantEntity, Long> {

        Optional<TenantEntity> findByTitle(String title);

        List<TenantEntity> findByTitleContainingIgnoreCase(String title);

        @Query("SELECT t FROM TenantEntity t")
        Page<TenantEntity> findAllWithPagination(Pageable pageable);

}