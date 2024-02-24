package com.pragma.powerup_usermicroservice.domain.usecase;

import com.pragma.powerup_usermicroservice.domain.api.IJwtServicePort;
import com.pragma.powerup_usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup_usermicroservice.domain.clientapi.ISmallSquareMSClientPort;
import com.pragma.powerup_usermicroservice.domain.exception.OwnerMustBe18yo;
import com.pragma.powerup_usermicroservice.domain.exception.RestaurantEmployeeAssignErrorException;
import com.pragma.powerup_usermicroservice.domain.exception.RestaurantOwnerInvalidException;
import com.pragma.powerup_usermicroservice.domain.model.User;
import com.pragma.powerup_usermicroservice.domain.spi.IUserPersistencePort;

import java.time.LocalDate;

public class UserUseCase implements IUserServicePort {
    
    private final IUserPersistencePort userPersistencePort;
    private final ISmallSquareMSClientPort smallSquareMSClientPort;
    private final IJwtServicePort jwtServicePort;
    
    public UserUseCase(IUserPersistencePort userPersistencePort,
                       ISmallSquareMSClientPort smallSquareMSClientPort,
                       IJwtServicePort jwtServicePort) {
        this.userPersistencePort = userPersistencePort;
        this.smallSquareMSClientPort = smallSquareMSClientPort;
        this.jwtServicePort = jwtServicePort;
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
    public User getOwnerById(Long id) {
        return userPersistencePort.getOwnerById(id);
    }
    
    @Override
    public void createEmployee(String authHeader, User user, Long idRestaurant) {
        boolean isOwner = smallSquareMSClientPort.verifyRestaurantOwnership(authHeader, idRestaurant);
        
        if (!isOwner){
            throw new RestaurantOwnerInvalidException();
        }
        User newEmployee = userPersistencePort.createEmployee(user);
        if (!smallSquareMSClientPort.assignEmployeeToRestaurant(authHeader, newEmployee.getId(), idRestaurant)){
            throw new RestaurantEmployeeAssignErrorException();
        }
    }
}
