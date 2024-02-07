package com.pragma.powerup_usermicroservice.infrastructure.out.jpa.mapper;

import com.pragma.powerup_usermicroservice.domain.model.Role;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy =
        ReportingPolicy.IGNORE)
public interface IRoleEntityMapper {
    Role roleEntityToRole(RoleEntity roleEntity);
    
    List<Role> roleEntityListToRoleList(List<RoleEntity> roleEntityList);
    
    
}
