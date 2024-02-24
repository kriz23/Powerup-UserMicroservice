package com.pragma.powerup_usermicroservice.domain.clientapi;

public interface ISmallSquareMSClientPort {
    boolean verifyRestaurantOwnership(String authHeader, Long idRestaurant);
    boolean assignEmployeeToRestaurant(String authHeader, Long idEmployee, Long idRestaurant);
}
