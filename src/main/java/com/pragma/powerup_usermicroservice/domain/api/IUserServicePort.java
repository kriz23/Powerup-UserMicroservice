package com.pragma.powerup_usermicroservice.domain.api;

import com.pragma.powerup_usermicroservice.domain.model.User;

public interface IUserServicePort {
    boolean checkAge(User user);
    void createOwner(User user);
    User getOwnerById(Long id);
    void createEmployee(String authHeader, User user, Long idRestaurant);
}
