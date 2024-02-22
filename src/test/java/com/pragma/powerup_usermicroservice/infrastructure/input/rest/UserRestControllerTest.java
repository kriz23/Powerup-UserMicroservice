package com.pragma.powerup_usermicroservice.infrastructure.input.rest;

import com.pragma.powerup_usermicroservice.application.dto.request.OwnerRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.response.OwnerResponseDto;
import com.pragma.powerup_usermicroservice.application.dto.response.RoleResponseDto;
import com.pragma.powerup_usermicroservice.application.handler.IUserHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {
    @Mock
    private IUserHandler userHandler;
    
    @InjectMocks
    private UserRestController userRestController;
    
    
    @Test
    @DisplayName("Given a valid user when createOwner then return 201")
    void createOwner_validUser_return201() {
        OwnerRequestDto ownerRequestDto = new OwnerRequestDto("John", "Doe", "123456789", "+573101234567",
                                                              LocalDate.of(2009, 1, 1), "john.doe@gmail.com",
                                                              "plainpassword", 2L);
        Mockito.doNothing().when(userHandler).createOwner(ownerRequestDto);
        
        ResponseEntity<Void> responseEntity = userRestController.createOwner(ownerRequestDto);
        
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    
    @Test
    @DisplayName("Given a valid id when getOwner then return desired owner")
    void getOwner_validId_returnDesiredOwner() {
        OwnerResponseDto expectedOwner = new OwnerResponseDto(1L, "John", "Doe", "123456789", "+573101234567",
                                                              LocalDate.of(2000, 1, 1), "john.doe@gmail.com",
                                                              "$2a$10$Pl5xVXG4JkXsJZ4krcIMAuDIDNCk8HKqeJYQ8gUjE64QfHjR0aSeu",
                                                              new RoleResponseDto(2L, "OWNER", "Owner"));
        Mockito.when(userHandler.getOwnerById(1L)).thenReturn(expectedOwner);
        
        OwnerResponseDto actualOwner = userRestController.getOwner(1L).getBody();
        
        assertEquals(HttpStatus.OK, userRestController.getOwner(1L).getStatusCode());
        assertEquals(expectedOwner, actualOwner);
        
        
    }
}