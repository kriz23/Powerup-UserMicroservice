package com.pragma.powerup_usermicroservice.domain.api;

import com.pragma.powerup_usermicroservice.domain.model.User;

public interface IUserServicePort {
    boolean checkAge(User user);
    boolean validateName(String name);
    boolean validateSurname(String surname);
    boolean validateDocNumber(String docNumber);
    boolean validatePhone(String phone);
    boolean validateBirthdateFormat(String birthdate);
    boolean validateMail(String mail);
    boolean genericValidations(User user);
    void createOwner(User user);
    User getOwnerById(Long id);
    void createEmployee(String authHeader, User user, Long idRestaurant);
    void createClient(User user);
}
