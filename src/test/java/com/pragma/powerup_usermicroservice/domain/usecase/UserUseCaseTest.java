package com.pragma.powerup_usermicroservice.domain.usecase;

import com.pragma.powerup_usermicroservice.domain.exception.OwnerMustBe18yo;
import com.pragma.powerup_usermicroservice.domain.model.Role;
import com.pragma.powerup_usermicroservice.domain.model.User;
import com.pragma.powerup_usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;
    
    @InjectMocks
    private UserUseCase userUseCase;
    
    @Test
    @DisplayName("Check age of user under 18 throws OwnerMustBe18yo exception")
    void checkAge_userIsUnder18_throwsOwnerMustBe18yo() {
        User user = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(2009, 1, 1),
                             "john.doe@gmail.com", "plainpassword", new Role(2L, "OWNER", "Owner"));
        assertThrows(OwnerMustBe18yo.class, () -> userUseCase.createOwner(user));
    }
    
    @Test
    @DisplayName("Check age of user over 18 returns true")
    void checkAge_userIsOver18_returnsTrue() {
        User user = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(1990, 1, 1),
                             "john.doe@gmail.com", "plainpassword", new Role(2L, "OWNER", "Owner"));
        assert (userUseCase.checkAge(user));
    }
    
    
    @Test
    @DisplayName("Create owner with valid owner calls persistence port")
    void createOwner_validOwner_callsPersistencePort() {
        User user = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(1990, 1, 1),
                             "john.doe@gmail.com", "plainpassword", new Role(2L, "OWNER", "Owner"));
        userUseCase.createOwner(user);
        
        Mockito.verify(userPersistencePort).createOwner(user);
    }
    
    @Test
    @DisplayName("Create owner with invalid owner throws OwnerMustBe18yo")
    void createOwner_invalidOwner_throwsOwnerMustBe18yo() {
        User user = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(2009, 1, 1),
                             "john.doe@gmail.com", "plainpassword", new Role(2L, "OWNER", "Owner"));
        assertThrows(OwnerMustBe18yo.class, () -> userUseCase.createOwner(user));
    }
    
    @Test
    @DisplayName("Get owner with valid id calls persistence port")
    void getOwner_validId_callsPersistencePort() {
        userUseCase.getOwner(1L);
        Mockito.verify(userPersistencePort).getOwner(1L);
    }
    
}