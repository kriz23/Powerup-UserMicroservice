package com.pragma.powerup_usermicroservice.infrastructure.configuration;

import com.pragma.powerup_usermicroservice.domain.api.IRoleServicePort;
import com.pragma.powerup_usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup_usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup_usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup_usermicroservice.domain.usecase.RoleUseCase;
import com.pragma.powerup_usermicroservice.domain.usecase.UserUseCase;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.repository.IRoleRepository;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    
    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }
    
    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }
    
    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }
    
    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }
    
}