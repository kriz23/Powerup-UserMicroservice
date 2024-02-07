package com.pragma.powerup_usermicroservice.application.handler.impl;

import com.pragma.powerup_usermicroservice.application.dto.request.OwnerRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.response.OwnerResponseDto;
import com.pragma.powerup_usermicroservice.application.handler.IUserHandler;
import com.pragma.powerup_usermicroservice.application.mapper.IUserRequestMapper;
import com.pragma.powerup_usermicroservice.application.mapper.IUserResponseMapper;
import com.pragma.powerup_usermicroservice.application.service.PasswordManager;
import com.pragma.powerup_usermicroservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {
    
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final PasswordManager passwordManager;
    
    @Override
    public void createOwner(OwnerRequestDto ownerRequestDto) {
        ownerRequestDto.setPassword(passwordManager.hashPassword(ownerRequestDto.getPassword()));
        userServicePort.createOwner(userRequestMapper.ownerRequestDtoToUser(ownerRequestDto));
    }
    
    @Override
    public OwnerResponseDto getOwner(Long idOwner) {
        return userResponseMapper.userToOwnerResponseDto(userServicePort.getOwner(idOwner));
    }
}
