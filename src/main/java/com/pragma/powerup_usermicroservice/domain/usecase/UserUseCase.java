package com.pragma.powerup_usermicroservice.domain.usecase;

import com.pragma.powerup_usermicroservice.domain.api.IJwtServicePort;
import com.pragma.powerup_usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup_usermicroservice.domain.clientapi.ISmallSquareMSClientPort;
import com.pragma.powerup_usermicroservice.domain.exception.*;
import com.pragma.powerup_usermicroservice.domain.model.User;
import com.pragma.powerup_usermicroservice.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class UserUseCase implements IUserServicePort {
    
    private final IUserPersistencePort userPersistencePort;
    private final ISmallSquareMSClientPort smallSquareMSClientPort;
    private final IJwtServicePort jwtServicePort;
    
    public UserUseCase(IUserPersistencePort userPersistencePort, ISmallSquareMSClientPort smallSquareMSClientPort,
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
    public boolean validateName(String name) {
        Pattern pattern = Pattern.compile("^[\\p{L} ]{2,50}$");
        if (!pattern.matcher(name).matches() || name.isEmpty()) {
            throw new UserNameInvalidException();
        }
        return true;
    }
    
    @Override
    public boolean validateSurname(String surname) {
        Pattern pattern = Pattern.compile("^[\\p{L} ]{2,50}$");
        if (!pattern.matcher(surname).matches() || surname.isEmpty()) {
            throw new UserSurnameInvalidException();
        }
        return true;
    }
    
    @Override
    public boolean validateDocNumber(String docNumber) {
        Pattern pattern = Pattern.compile("^\\d{7,20}$");
        if (!pattern.matcher(docNumber).matches() || docNumber.isEmpty()) {
            throw new UserDocNumberInvalidException();
        }
        return true;
    }
    
    @Override
    public boolean validatePhone(String phone) {
        Pattern pattern = Pattern.compile("^\\+573\\d{9}$");
        if (!pattern.matcher(phone).matches() || phone.isEmpty()) {
            throw new UserPhoneInvalidException();
        }
        return true;
    }
    
    @Override
    public boolean validateBirthdateFormat(String birthdate) {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        if (!pattern.matcher(birthdate).matches() || birthdate.isEmpty()) {
            throw new UserBirthdateFormatInvalidException();
        }
        return true;
    }
    
    @Override
    public boolean validateMail(String mail) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
        if (!pattern.matcher(mail).matches() || mail.isEmpty()) {
            throw new UserMailInvalidException();
        }
        return true;
    }
    
    @Override
    public boolean genericValidations(User user) {
        return validateName(user.getName()) && validateSurname(user.getSurname()) && validateDocNumber(
                user.getDocNumber()) && validatePhone(user.getPhone()) && validateMail(user.getMail());
    }
    
    @Override
    public void createOwner(User user) {
        if (checkAge(user) && genericValidations(user)) {
            userPersistencePort.createOwner(user);
        }
    }
    
    @Override
    public User getOwnerById(Long id) {
        return userPersistencePort.getOwnerById(id);
    }
    
    @Override
    public void createEmployee(String authHeader, User user, Long idRestaurant) {
        if (genericValidations(user)) {
            boolean isOwner = smallSquareMSClientPort.verifyRestaurantOwnership(authHeader, idRestaurant);
            
            if (!isOwner) {
                throw new RestaurantOwnerInvalidException();
            }
            User newEmployee = userPersistencePort.createEmployee(user);
            if (!smallSquareMSClientPort.assignEmployeeToRestaurant(authHeader, newEmployee.getId(), idRestaurant)) {
                throw new RestaurantEmployeeAssignErrorException();
            }
        }
    }
    
    @Override
    public void createClient(User user) {
        if (genericValidations(user)){
            userPersistencePort.createClient(user);
        }
    
    }
}
