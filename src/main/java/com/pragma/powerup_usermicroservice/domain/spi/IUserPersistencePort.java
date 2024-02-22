package com.pragma.powerup_usermicroservice.domain.spi;

import com.pragma.powerup_usermicroservice.domain.model.User;

public interface IUserPersistencePort {
    void createOwner(User user);
    User getOwnerById(Long id);
    User getUserByMail(String authHeader, String mail);
}
