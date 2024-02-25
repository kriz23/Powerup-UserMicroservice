package com.pragma.powerup_usermicroservice.domain.usecase;

import com.pragma.powerup_usermicroservice.domain.clientapi.ISmallSquareMSClientPort;
import com.pragma.powerup_usermicroservice.domain.exception.*;
import com.pragma.powerup_usermicroservice.domain.model.Role;
import com.pragma.powerup_usermicroservice.domain.model.User;
import com.pragma.powerup_usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;
    
    @Mock
    private ISmallSquareMSClientPort smallSquareMSClientPort;
    
    @InjectMocks
    private UserUseCase userUseCase;
    
    @Test
    @DisplayName("Check age of user under 18 throws OwnerMustBe18yo exception")
    void checkAge_userIsUnder18_throwsOwnerMustBe18yo() {
        User user = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(2009, 1, 1),
                             "john.doe@gmail.com", "plainpassword", new Role(2L, "OWNER", "Owner"));
        assertThrows(OwnerMustBe18yo.class, () -> userUseCase.checkAge(user));
    }
    
    @Test
    @DisplayName("Check age of user over 18 returns true")
    void checkAge_userIsOver18_returnsTrue() {
        User user = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(1990, 1, 1),
                             "john.doe@gmail.com", "plainpassword", new Role(2L, "ROLE_OWNER", "Owner"));
        assert (userUseCase.checkAge(user));
    }
    
    @Test
    void validateName_validName_returnsTrue(){
        String name = "John";
        
        assertTrue(userUseCase.validateName(name));
    }
    
    @Test
    void validateName_invalidName_throwsException(){
        String name = "";
        
        assertThrows(UserNameInvalidException.class, () -> userUseCase.validateName(name));
    }
    
    @Test
    void validateSurname_validSurname_returnsTrue(){
        String surname = "Doe";
        
        assertTrue(userUseCase.validateSurname(surname));
    }
    
    @Test
    void validateSurname_invalidSurname_throwsException(){
        String surname = "";
        
        assertThrows(UserSurnameInvalidException.class, () -> userUseCase.validateSurname(surname));
    }
    
    @Test
    void validateDocNumber_validDocNumber_returnsTrue(){
        String docNumber = "123456789";
        
        assertTrue(userUseCase.validateDocNumber(docNumber));
    }
    
    @Test
    void validateDocNumber_invalidDocNumber_throwsException(){
        String docNumber = "";
        
        assertThrows(UserDocNumberInvalidException.class, () -> userUseCase.validateDocNumber(docNumber));
    }
    
    @Test
    void validatePhone_validPhone_returnsTrue(){
        String phone = "+573101234567";
        
        assertTrue(userUseCase.validatePhone(phone));
    }
    
    @Test
    void validatePhone_invalidPhone_throwsException(){
        String phone = "";
        
        assertThrows(UserPhoneInvalidException.class, () -> userUseCase.validatePhone(phone));
    }
    
    @Test
    void validateBirthdateFormat_validBithdateFormat_returnsTrue(){
        String birthdate = "1990-01-01";
        
        assertTrue(userUseCase.validateBirthdateFormat(birthdate));
    }
    
    @Test
    void validateBirthdateFormat_invalidBirthdateFormat_throwsException(){
        String birthdate = "";
        
        assertThrows(UserBirthdateFormatInvalidException.class, () -> userUseCase.validateBirthdateFormat(birthdate));
    }
    
    @Test
    void validateMail_validMail_returnsTrue(){
        String mail = "john.doe@gmail.com";
        
        assertTrue(userUseCase.validateMail(mail));
    }
    
    @Test
    void validateMail_invalidMail_throwsException(){
        String mail = "";
        
        assertThrows(UserMailInvalidException.class, () -> userUseCase.validateMail(mail));
    }
    
    @Test
    void checkGenericValidations_allValid_returnsTrue(){
        User user = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(2009, 1, 1),
                             "john.doe@gmail.com", "plainpassword", new Role(1L, "ROLE_ADMIN", "Admin"));
        
        assertTrue(userUseCase.genericValidations(user));
    }
    
    
    @Test
    void createOwner_validOwner_callsPersistencePort() {
        User user = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(1990, 1, 1),
                             "john.doe@gmail.com", "plainpassword", new Role(2L, "ROLE_PROPIETARIO", "Owner"));
        userUseCase.createOwner(user);
        
        verify(userPersistencePort, times(1)).createOwner(user);
    }
    
    @Test
    void createOwner_invalidOwner_throwsOwnerMustBe18yo() {
        User user = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(2009, 1, 1),
                             "john.doe@gmail.com", "plainpassword", new Role(2L, "ROLE_PROPIETARIO", "Owner"));
        assertThrows(OwnerMustBe18yo.class, () -> userUseCase.createOwner(user));
    }
    
    @Test
    void getOwnerById_validId_callsPersistencePort() {
        userUseCase.getOwnerById(1L);
        verify(userPersistencePort, times(1)).getOwnerById(1L);
    }
    
    @Test
    void createEmployee_allValid_callsPersistencePort(){
        String authHeader = "validToken";
        User newEmployee = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(1990, 1, 1),
                                    "john.doe@gmail.com", "plainpassword", new Role(3L, "ROLE_EMPLEADO", "Empleado"));
        Long idRestaurant = 1L;
        when(smallSquareMSClientPort.verifyRestaurantOwnership(authHeader, idRestaurant)).thenReturn(true);
        when(userPersistencePort.createEmployee(newEmployee)).thenReturn(newEmployee);
        when(smallSquareMSClientPort.assignEmployeeToRestaurant(authHeader, newEmployee.getId(), idRestaurant)).thenReturn(true);
        userUseCase.createEmployee(authHeader, newEmployee, idRestaurant);
        verify(userPersistencePort, times(1)).createEmployee(newEmployee);
    }
    
    @Test
    void createEmployee_invalidRestaurantOwnership_throwsException(){
        String authHeader = "invalidToken";
        User newEmployee = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(1990, 1, 1),
                                    "john.doe@gmail.com", "plainpassword", new Role(3L, "ROLE_EMPLEADO", "Empleado"));
        Long idRestaurant = 1L;
        when(smallSquareMSClientPort.verifyRestaurantOwnership(authHeader, idRestaurant)).thenReturn(false);
        
        assertThrows(RestaurantOwnerInvalidException.class, () -> userUseCase.createEmployee(authHeader, newEmployee, idRestaurant));
    }
    
    @Test
    void createEmployee_assignEmployeeToRestaurantFails_throwsException(){
        String authHeader = "validToken";
        User newEmployee = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(1990, 1, 1),
                                    "john.doe@gmail.com", "plainpassword", new Role(3L, "ROLE_EMPLEADO", "Empleado"));
        Long idRestaurant = 1L;
        when(smallSquareMSClientPort.verifyRestaurantOwnership(authHeader, idRestaurant)).thenReturn(true);
        when(userPersistencePort.createEmployee(newEmployee)).thenReturn(newEmployee);
        when(smallSquareMSClientPort.assignEmployeeToRestaurant(authHeader, newEmployee.getId(), idRestaurant)).thenReturn(false);
        
        assertThrows(RestaurantEmployeeAssignErrorException.class, () -> userUseCase.createEmployee(authHeader, newEmployee, idRestaurant));
    }
    
}