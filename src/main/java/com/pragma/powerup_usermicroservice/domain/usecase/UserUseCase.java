package com.pragma.powerup_usermicroservice.domain.usecase;

import com.pragma.powerup_usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup_usermicroservice.domain.exception.OwnerMustBe18yo;
import com.pragma.powerup_usermicroservice.domain.model.User;
import com.pragma.powerup_usermicroservice.domain.spi.IUserPersistencePort;

import java.time.LocalDate;

public class UserUseCase implements IUserServicePort {
    
    private final IUserPersistencePort userPersistencePort;
    
    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    
    @Override
    public boolean checkAge(User user) {
        LocalDate today = LocalDate.now();
        
        if (user.getBirthdate().isAfter(today.minusYears(18))) {
            throw new OwnerMustBe18yo();
        }
        return true;
    }
    
    @Override
    public void createOwner(User user) {
        if (checkAge(user)) {
            userPersistencePort.createOwner(user);
        }
    }
    
    @Override
    public User getOwner(Long id) {
        return userPersistencePort.getOwner(id);
    }
}
