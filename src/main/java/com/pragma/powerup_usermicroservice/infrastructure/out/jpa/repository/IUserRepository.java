package com.pragma.powerup_usermicroservice.infrastructure.out.jpa.repository;

import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByDocNumber(String docNumber);
    boolean existsByDocNumber(String docNumber);
    Optional<UserEntity> findByMail(String mail);
    boolean existsByMail(String mail);
}
