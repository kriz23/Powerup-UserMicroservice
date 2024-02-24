package com.pragma.powerup_usermicroservice.infrastructure.out.http.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "powerup-smallsquaremicroservice", url = "${powerup_smallsquaremicroservice_url}", configuration
        = FeignClientConfiguration.class)
public interface ISmallSquareFeignClient {
    @GetMapping("/smallsquare/restaurants/verify-owner/{id}")
    Boolean verifyRestaurantOwnership(@RequestHeader("Authorization") String bearerToken,
                                      @PathVariable("id") Long idRestaurant);
    
    @PostMapping("/smallsquare/restaurants/employee")
    Boolean assignEmployeeToRestaurant(@RequestHeader("Authorization") String bearerToken,
                                    @RequestParam("idEmployee") Long idEmployee,
                                    @RequestParam("idRestaurant") Long idRestaurant);
}
