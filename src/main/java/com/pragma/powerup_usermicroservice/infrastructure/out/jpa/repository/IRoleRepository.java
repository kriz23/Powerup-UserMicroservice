package com.pragma.powerup_usermicroservice.infrastructure.out.jpa.repository;

import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

}
