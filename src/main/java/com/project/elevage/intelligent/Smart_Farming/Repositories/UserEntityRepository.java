package com.project.elevage.intelligent.Smart_Farming.Repositories;

import com.project.elevage.intelligent.Smart_Farming.Entities.Authority;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByAuthority(Authority authority);
}