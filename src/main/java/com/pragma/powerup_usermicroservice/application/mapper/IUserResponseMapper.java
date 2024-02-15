package com.pragma.powerup_usermicroservice.application.mapper;

import com.pragma.powerup_usermicroservice.application.dto.response.OwnerResponseDto;
import com.pragma.powerup_usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy =
        ReportingPolicy.IGNORE, uses = {IRoleResponseMapper.class})
public interface IUserResponseMapper {
    IRoleResponseMapper INSTANCE = Mappers.getMapper(IRoleResponseMapper.class);
    
    @Mapping(source = "role.id", target = "role.id")
    @Mapping(source = "role.name", target = "role.name")
    @Mapping(source = "role.description", target = "role.description")
    OwnerResponseDto userToOwnerResponseDto(User user);
    
    List<OwnerResponseDto> userToOwnerResponseDtoList(List<User> userList);
    
    
}
