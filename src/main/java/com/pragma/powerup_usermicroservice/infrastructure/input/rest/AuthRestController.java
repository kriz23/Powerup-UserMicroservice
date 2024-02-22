package com.pragma.powerup_usermicroservice.infrastructure.input.rest;

import com.pragma.powerup_usermicroservice.application.dto.request.LoginRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.response.JwtResponseDto;
import com.pragma.powerup_usermicroservice.infrastructure.configuration.security.authhandler.IAuthHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Auth related endpoints")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
    
    private final IAuthHandler authHandler;
    
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(
            @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authHandler.login(loginRequestDto));
    }
}
