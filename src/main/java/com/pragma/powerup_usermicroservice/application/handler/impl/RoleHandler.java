package com.pragma.powerup_usermicroservice.application.handler.impl;

import com.pragma.powerup_usermicroservice.application.dto.response.RoleResponseDto;
import com.pragma.powerup_usermicroservice.application.handler.IRoleHandler;
import com.pragma.powerup_usermicroservice.application.mapper.IRoleResponseMapper;
import com.pragma.powerup_usermicroservice.domain.api.IRoleServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleHandler implements IRoleHandler {
    
    private final IRoleServicePort roleServicePort;
    private final IRoleResponseMapper roleResponseMapper;
    
    @Override
    public RoleResponseDto getRole(Long idRole) {
        return roleResponseMapper.roleToRoleResponseDto(roleServicePort.getRoleById(idRole));
    }
    
    @Override
    public List<RoleResponseDto> getAllRoles() {
        return roleResponseMapper.roleToRoleResponseDtoList(roleServicePort.getAllRoles());
    }
}
