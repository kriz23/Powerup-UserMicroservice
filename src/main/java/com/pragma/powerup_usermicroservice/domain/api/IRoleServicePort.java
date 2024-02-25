package com.pragma.powerup_usermicroservice.domain.api;

import com.pragma.powerup_usermicroservice.domain.model.Role;

import java.util.List;

public interface IRoleServicePort {
    Role getRoleById(Long idRole);
    
    List<Role> getAllRoles();
    
}
