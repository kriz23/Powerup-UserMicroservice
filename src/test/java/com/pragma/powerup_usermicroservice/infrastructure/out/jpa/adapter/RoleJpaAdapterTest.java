package com.pragma.powerup_usermicroservice.infrastructure.out.jpa.adapter;

import com.pragma.powerup_usermicroservice.domain.model.Role;
import com.pragma.powerup_usermicroservice.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.repository.IRoleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoleJpaAdapterTest {
    @Mock
    private IRoleRepository roleRepository;
    @Mock
    private IRoleEntityMapper roleEntityMapper;
    @InjectMocks
    private RoleJpaAdapter roleJpaAdapter;
    
    @Test
    @DisplayName("Given a role id, when getRole, then return role")
    void getRole() {
        RoleEntity roleEntity1 = new RoleEntity(1L, "ADMIN", "Administrador");
        Role role1 = new Role(1L, "ADMIN", "Administrador");
        
        Mockito.when(roleRepository.findById(1L)).thenReturn(Optional.of(roleEntity1));
        Mockito.when(roleEntityMapper.roleEntityToRole(roleEntity1)).thenReturn(role1);
        
        assertEquals(role1, roleJpaAdapter.getRole(1L));
    }
    
    @Test
    @DisplayName("Given a role id, when getRole, then throw NoDataFoundException")
    void getRole_throwNoDataFoundException() {
        Mockito.when(roleRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(NoDataFoundException.class, () -> roleJpaAdapter.getRole(1L));
    }
    
    @Test
    @DisplayName("Given a role list, when getAllRoles, then return role list")
    void getAllRolesSuccesfully(){
        List<RoleEntity> roleEntityList = List.of(new RoleEntity(1L, "ADMIN", "Administrador"),
                                                  new RoleEntity(2L, "PROPIETARIO", "Propietario"),
                                                  new RoleEntity(3L, "EMPLEADO", "Empleado"),
                                                  new RoleEntity(4L, "CLIENTE", "Cliente"));
        
        List<Role> roleList = List.of(new Role(1L, "ADMIN", "Administrador"),
                                      new Role(2L, "PROPIETARIO", "Propietario"),
                                      new Role(3L, "EMPLEADO", "Empleado"),
                                      new Role(4L, "CLIENTE", "Cliente"));
        
        Mockito.when(roleRepository.findAll()).thenReturn(roleEntityList);
        Mockito.when(roleEntityMapper.roleEntityListToRoleList(roleEntityList)).thenReturn(roleList);
        
        assertEquals(roleList, roleJpaAdapter.getAllRoles());
    }
    
    @Test
    @DisplayName("Given an empty role list, when getAllRoles, then throw NoDataFoundException")
    void getAllRoles_throwNoDataFoundException(){
        List<RoleEntity> roleEntityList = List.of();
        
        Mockito.when(roleRepository.findAll()).thenReturn(roleEntityList);
        
        assertThrows(NoDataFoundException.class, () -> roleJpaAdapter.getAllRoles());
    }
}