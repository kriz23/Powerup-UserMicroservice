package com.pragma.powerup_usermicroservice.infrastructure.out.jpa.adapter;

import com.pragma.powerup_usermicroservice.domain.model.Role;
import com.pragma.powerup_usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup_usermicroservice.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {
    
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    
    @Override
    public Role getRoleById(Long idRole) {
        RoleEntity roleEntity = roleRepository.findById(idRole).orElseThrow(NoDataFoundException::new);
        return roleEntityMapper.roleEntityToRole(roleEntity);
    }
    
    @Override
    public List<Role> getAllRoles() {
        List<RoleEntity> roleEntityList = roleRepository.findAll();
        if (roleEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return roleEntityMapper.roleEntityListToRoleList(roleEntityList);
    }
    
}
