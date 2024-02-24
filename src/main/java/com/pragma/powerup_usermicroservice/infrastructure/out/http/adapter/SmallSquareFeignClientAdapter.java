package com.pragma.powerup_usermicroservice.infrastructure.out.http.adapter;

import com.pragma.powerup_usermicroservice.domain.clientapi.ISmallSquareMSClientPort;
import com.pragma.powerup_usermicroservice.infrastructure.out.http.feignclient.ISmallSquareFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SmallSquareFeignClientAdapter implements ISmallSquareMSClientPort {
    
    private final ISmallSquareFeignClient smallSquareFeignClient;
    
    @Override
    public boolean verifyRestaurantOwnership(String authHeader, Long idRestaurant) {
        return smallSquareFeignClient.verifyRestaurantOwnership(authHeader, idRestaurant);
    }
    
    @Override
    public boolean assignEmployeeToRestaurant(String authHeader, Long idEmployee, Long idRestaurant) {
        return smallSquareFeignClient.assignEmployeeToRestaurant(authHeader, idEmployee, idRestaurant);
    }
}
