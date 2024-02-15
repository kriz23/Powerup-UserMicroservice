package com.pragma.powerup_usermicroservice.application.handler;

import com.pragma.powerup_usermicroservice.application.dto.request.OwnerRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.response.OwnerResponseDto;

public interface IUserHandler {
    void createOwner(OwnerRequestDto ownerRequestDto);
    
    OwnerResponseDto getOwner(Long idOwner);
}
