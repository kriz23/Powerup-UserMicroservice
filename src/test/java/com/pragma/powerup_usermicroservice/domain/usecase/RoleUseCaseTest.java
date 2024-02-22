package com.pragma.powerup_usermicroservice.domain.usecase;

import com.pragma.powerup_usermicroservice.domain.model.Role;
import com.pragma.powerup_usermicroservice.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {
    @Mock
    private IRolePersistencePort rolePersistencePort;
    
    @InjectMocks
    private RoleUseCase roleUseCase;
    
    @Test
    @DisplayName("Get role with valid id calls persistence port")
    void getRole_validId_callsPersistencePort(){
        roleUseCase.getRoleById(1L);
        Mockito.verify(rolePersistencePort).getRoleById(1L);
    }
    
    @Test
    @DisplayName("Get all roles calls persistence port")
    void getAllRoles_callsPersistencePort(){
        List<Role> expectedRoles = List.of(new Role(1L, "ADMIN", "Administrador"),
                                           new Role(2L, "PROPIETARIO", "Propietario"),
                                           new Role(3L, "EMPLEADO", "Empleado"),
                                           new Role(4L, "CLIENTE", "Cliente"));
        Mockito.when(rolePersistencePort.getAllRoles()).thenReturn(expectedRoles);
        
        List<Role> actualRoles = roleUseCase.getAllRoles();
        
        assertEquals(expectedRoles, actualRoles);
        assertTrue(actualRoles.containsAll(expectedRoles));
    }
    
    @Test
    @DisplayName("Get all roles returns empty list")
    void getAllRoles_returnEmptyList(){
        List<Role> expectedRoles = List.of();
        Mockito.when(rolePersistencePort.getAllRoles()).thenReturn(expectedRoles);
        
        List<Role> actualRoles = roleUseCase.getAllRoles();
        
        assertTrue(actualRoles.isEmpty());
    }
}