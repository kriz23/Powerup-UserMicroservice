package com.pragma.powerup_usermicroservice.domain.usecase;

import com.pragma.powerup_usermicroservice.domain.api.IRoleServicePort;
import com.pragma.powerup_usermicroservice.domain.model.Role;
import com.pragma.powerup_usermicroservice.domain.spi.IRolePersistencePort;

import java.util.List;

public class RoleUseCase implements IRoleServicePort {
    
    private final IRolePersistencePort rolePersistencePort;
    
    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }
    
    @Override
    public Role getRoleById(Long idRole) {
        return rolePersistencePort.getRoleById(idRole);
    }
    
    @Override
    public List<Role> getAllRoles() {
        return rolePersistencePort.getAllRoles();
    }
    
}
