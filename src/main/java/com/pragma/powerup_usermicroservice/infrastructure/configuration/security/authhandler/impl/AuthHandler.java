package com.pragma.powerup_usermicroservice.infrastructure.configuration.security.authhandler.impl;

import com.pragma.powerup_usermicroservice.application.dto.request.ClientRegisterRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.request.LoginRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.response.JwtResponseDto;
import com.pragma.powerup_usermicroservice.application.handler.IUserHandler;
import com.pragma.powerup_usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup_usermicroservice.infrastructure.configuration.security.authhandler.IAuthHandler;
import com.pragma.powerup_usermicroservice.domain.api.IJwtServicePort;
import com.pragma.powerup_usermicroservice.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthHandler implements IAuthHandler {
    
    private final AuthenticationManager authenticationManager;
    private final IJwtServicePort jwtServicePort;
    private final IUserRepository userRepository;
    private final IUserHandler userHandler;
    
    @Override
    public JwtResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getMail(), loginRequestDto.getPassword()));
        UserEntity user = userRepository.findByMail(loginRequestDto.getMail()).orElseThrow(NoDataFoundException::new);
        String token = jwtServicePort.generateToken(user);
        return new JwtResponseDto(token);
    }
    
    @Override
    public void register(ClientRegisterRequestDto clientRegisterRequestDto) {
        userHandler.createClient(clientRegisterRequestDto);
    }
}
