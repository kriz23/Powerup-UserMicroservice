package com.pragma.powerup_usermicroservice.infrastructure.configuration.security.authhandler;

import com.pragma.powerup_usermicroservice.application.dto.request.ClientRegisterRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.request.LoginRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.response.JwtResponseDto;

public interface IAuthHandler {
    JwtResponseDto login(LoginRequestDto loginRequestDto);
    void register(ClientRegisterRequestDto clientRegisterRequestDto);
}
