package com.pragma.powerup_usermicroservice.infrastructure.input.rest;

import com.pragma.powerup_usermicroservice.application.dto.response.RoleResponseDto;
import com.pragma.powerup_usermicroservice.application.handler.IRoleHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoleRestControllerTest {
    @Mock
    private IRoleHandler roleHandler;
    
    @InjectMocks
    private RoleRestController roleRestController;
    
    @Test
    @DisplayName("Get role by id")
    void getRole(){
        RoleResponseDto expectedRole = new RoleResponseDto(1L, "ADMIN", "Administrador");
        Mockito.when(roleHandler.getRole(1L)).thenReturn(expectedRole);
        
        RoleResponseDto actualRole = roleRestController.getRole(1L).getBody();
        
        assertEquals(HttpStatus.OK, roleRestController.getRole(1L).getStatusCode());
        assertEquals(expectedRole, actualRole);
    }
    
    @Test
    @DisplayName("Get all roles")
    void getAllRoles(){
        List<RoleResponseDto> expectedRoles = List.of(new RoleResponseDto(1L, "ADMIN", "Administrador"),
                                                      new RoleResponseDto(2L, "PROPIETARIO", "Propietario"),
                                                      new RoleResponseDto(3L, "EMPLEADO", "Empleado"),
                                                      new RoleResponseDto(4L, "CLIENTE", "Cliente"));
        Mockito.when(roleHandler.getAllRoles()).thenReturn(expectedRoles);
        
        List<RoleResponseDto> actualRoles = roleRestController.getAllRoles().getBody();
        
        assertEquals(HttpStatus.OK, roleRestController.getAllRoles().getStatusCode());
        assertEquals(expectedRoles, actualRoles);

    }

}