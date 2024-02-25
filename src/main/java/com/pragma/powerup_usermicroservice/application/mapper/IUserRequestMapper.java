package com.pragma.powerup_usermicroservice.application.mapper;

import com.pragma.powerup_usermicroservice.application.dto.request.ClientRegisterRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.request.OwnerRequestDto;
import com.pragma.powerup_usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy =
        ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    @Mapping(source = "idRole", target = "role.id")
    User ownerRequestDtoToUser(OwnerRequestDto ownerRequestDto);
    
    @Mapping(source = "idRole", target = "role.id")
    User employeeRequestDtoToUser(EmployeeRequestDto employeeRequestDto);
    
    @Mapping(source = "idRole", target = "role.id")
    User clientRegisterRequestDtoToUser(ClientRegisterRequestDto clientRegisterRequestDto);
    
}
