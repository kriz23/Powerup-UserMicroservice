package com.pragma.powerup_usermicroservice.infrastructure.out.jpa.adapter;

import com.pragma.powerup_usermicroservice.domain.api.IJwtServicePort;
import com.pragma.powerup_usermicroservice.domain.model.User;
import com.pragma.powerup_usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup_usermicroservice.infrastructure.exception.MailAlreadyRegistered;
import com.pragma.powerup_usermicroservice.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup_usermicroservice.infrastructure.exception.RequestMailInvalidException;
import com.pragma.powerup_usermicroservice.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final IJwtServicePort jwtServicePort;
    
    @Override
    public void createOwner(User user) {
        if (userRepository.findByDocNumber(user.getDocNumber()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (userRepository.existsByMail(user.getMail())) {
            throw new MailAlreadyRegistered();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntityMapper.userToUserEntity(user));
    }
    
    @Override
    public User getOwnerById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(NoDataFoundException::new);
        return userEntityMapper.userEntityToUser(userEntity);
    }
    
    @Override
    public User createEmployee(User user) {
        if (userRepository.findByDocNumber(user.getDocNumber()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (userRepository.existsByMail(user.getMail())) {
            throw new MailAlreadyRegistered();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userEntityMapper.userEntityToUser(userRepository.save(userEntityMapper.userToUserEntity(user)));
    }
    
    @Override
    public User getUserByMail(String authHeader, String mail) {
        String requestUserMail = jwtServicePort.getMailFromToken(jwtServicePort.getTokenFromHeader(authHeader));
        if (!requestUserMail.equals(mail)){
            throw new RequestMailInvalidException();
        }
        UserEntity userEntity = userRepository.findByMail(mail).orElseThrow(NoDataFoundException::new);
        return userEntityMapper.userEntityToUser(userEntity);
    }
    
}
