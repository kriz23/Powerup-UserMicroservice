package com.pragma.powerup_usermicroservice.domain.spi;

import com.pragma.powerup_usermicroservice.domain.model.Role;

import java.util.List;

public interface IRolePersistencePort {
    Role getRole(Long idRole);
    
    List<Role> getAllRoles();
    
}
