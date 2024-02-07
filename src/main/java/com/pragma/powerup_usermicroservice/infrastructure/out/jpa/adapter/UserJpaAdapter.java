package com.pragma.powerup_usermicroservice.infrastructure.out.jpa.adapter;

import com.pragma.powerup_usermicroservice.domain.model.User;
import com.pragma.powerup_usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup_usermicroservice.infrastructure.exception.MailAlreadyRegistered;
import com.pragma.powerup_usermicroservice.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup_usermicroservice.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    
    @Override
    public void createOwner(User user) {
        if (userRepository.findByDocNumber(user.getDocNumber()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (userRepository.existsByMail(user.getMail())) {
            throw new MailAlreadyRegistered();
        }
        userRepository.save(userEntityMapper.userToUserEntity(user));
    }
    
    @Override
    public User getOwner(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(NoDataFoundException::new);
        return userEntityMapper.userEntityToUser(userEntity);
    }
}
