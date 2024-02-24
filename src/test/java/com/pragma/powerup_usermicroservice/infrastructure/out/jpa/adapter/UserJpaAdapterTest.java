package com.pragma.powerup_usermicroservice.infrastructure.out.jpa.adapter;

import com.pragma.powerup_usermicroservice.domain.model.Role;
import com.pragma.powerup_usermicroservice.domain.model.User;
import com.pragma.powerup_usermicroservice.infrastructure.exception.MailAlreadyRegistered;
import com.pragma.powerup_usermicroservice.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserJpaAdapterTest {
    @Mock
    private IUserRepository userRepository;
    @Mock
    private IUserEntityMapper userEntityMapper;
    @InjectMocks
    private UserJpaAdapter userJpaAdapter;
    
    private User user;
    
    @BeforeEach
    void setUp(){
        user = new User(1L, "John", "Doe", "123456789", "+573101234567", LocalDate.of(1990, 1, 1),
                             "john.doe@gmail.com", "plainpassword", new Role(2L, "OWNER", "Owner"));
    }
    
    @Test
    @DisplayName("Given a user when createOwner, then save user")
    void createOwnerTest() {
        UserEntity userEntity = new UserEntity(1L, "John", "Doe", "123456789", "+573101234567",
                                               LocalDate.of(1990, 1, 1), "john.doe@gmail.com", "plainpassword",
                                               new RoleEntity(2L, "OWNER", "Owner"));
        
        Mockito.when(userRepository.findByDocNumber(user.getDocNumber())).thenReturn(Optional.empty());
        Mockito.when(userRepository.existsByMail(user.getMail())).thenReturn(false);
        Mockito.when(userEntityMapper.userToUserEntity(user)).thenReturn(userEntity);
        
        userJpaAdapter.createOwner(user);
        
        Mockito.verify(userRepository, times(1)).save(userEntity);
    }
    
    @Test
    @DisplayName("Given a user with existing docNumber when createOwner, then throw UserAlreadyExistsException")
    void createOwner_existingDocNumber_throwUserAlreadyExistsException() {
        Mockito.when(userRepository.findByDocNumber(user.getDocNumber())).thenReturn(Optional.of(new UserEntity()));
        
        assertThrows(UserAlreadyExistsException.class, () -> userJpaAdapter.createOwner(user));
    }
    
    @Test
    @DisplayName("Given a user with existing mail when createOwner, then throw MailAlreadyRegistered")
    void createOwner_existingMail_throwMailAlreadyRegistered() {
        Mockito.when(userRepository.findByDocNumber(user.getDocNumber())).thenReturn(Optional.empty());
        Mockito.when(userRepository.existsByMail(user.getMail())).thenReturn(true);
        
        assertThrows(MailAlreadyRegistered.class, () -> userJpaAdapter.createOwner(user));
        
        
    }
    
    
}