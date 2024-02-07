package com.pragma.powerup_usermicroservice.application.handler;

import com.pragma.powerup_usermicroservice.application.dto.response.RoleResponseDto;

import java.util.List;

public interface IRoleHandler {
    RoleResponseDto getRole(Long idRole);
    
    List<RoleResponseDto> getAllRoles();
}
