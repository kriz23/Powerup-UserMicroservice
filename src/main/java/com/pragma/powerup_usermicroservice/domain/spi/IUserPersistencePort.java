package com.pragma.powerup_usermicroservice.domain.spi;

import com.pragma.powerup_usermicroservice.domain.model.User;

public interface IUserPersistencePort {
    void createOwner(User user);
    User getOwner(Long id);
}
