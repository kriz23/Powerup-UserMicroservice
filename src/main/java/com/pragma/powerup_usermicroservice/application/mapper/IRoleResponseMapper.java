package com.pragma.powerup_usermicroservice.application.mapper;

import com.pragma.powerup_usermicroservice.application.dto.response.RoleResponseDto;
import com.pragma.powerup_usermicroservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy =
        ReportingPolicy.IGNORE)
public interface IRoleResponseMapper {
    RoleResponseDto roleToRoleResponseDto(Role role);
    
    List<RoleResponseDto> roleToRoleResponseDtoList(List<Role> roleList);
}
