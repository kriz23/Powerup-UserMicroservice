package com.pragma.powerup_usermicroservice.infrastructure.out.jpa.mapper;

import com.pragma.powerup_usermicroservice.domain.model.User;
import com.pragma.powerup_usermicroservice.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy =
        ReportingPolicy.IGNORE)
public interface IUserEntityMapper {
    @Mapping(source = "role.id", target = "roleEntity.id")
    UserEntity userToUserEntity(User user);
    
    @Mapping(source = "roleEntity.id", target = "role.id")
    User userEntityToUser(UserEntity userEntity);
}
