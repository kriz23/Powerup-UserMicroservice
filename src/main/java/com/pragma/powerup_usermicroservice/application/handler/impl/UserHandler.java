package com.pragma.powerup_usermicroservice.application.handler.impl;

import com.pragma.powerup_usermicroservice.application.dto.request.ClientRegisterRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.request.OwnerRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.response.AdminResponseDto;
import com.pragma.powerup_usermicroservice.application.dto.response.LoggedUserResponseDto;
import com.pragma.powerup_usermicroservice.application.dto.response.OwnerResponseDto;
import com.pragma.powerup_usermicroservice.application.handler.IUserHandler;
import com.pragma.powerup_usermicroservice.application.mapper.IUserRequestMapper;
import com.pragma.powerup_usermicroservice.application.mapper.IUserResponseMapper;
import com.pragma.powerup_usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup_usermicroservice.domain.exception.UserPhoneInvalidException;
import com.pragma.powerup_usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {
    
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final IUserPersistencePort userPersistencePort;
    
    @Override
    public LoggedUserResponseDto getLoggedUserByMail(String mail, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return userResponseMapper.userToLoggedUserResponseDto(userPersistencePort.getUserByMail(authHeader, mail));
    }
    
    @Override
    public AdminResponseDto getAdminByMail(String mail, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return userResponseMapper.userToAdminResponseDto(userPersistencePort.getUserByMail(authHeader, mail));
    }
    
    @Override
    public OwnerResponseDto getOwnerByMail(String mail, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return userResponseMapper.userToOwnerResponseDto(userPersistencePort.getUserByMail(authHeader, mail));
    }
    
    @Override
    public void createOwner(OwnerRequestDto ownerRequestDto) {
        if (ownerRequestDto.getPhone().length() <= 10 && ownerRequestDto.getPhone().contains("+57")) {
            throw new UserPhoneInvalidException();
        }
        if (ownerRequestDto.getPhone().length() == 10 && !ownerRequestDto.getPhone().contains("+57")) {
            ownerRequestDto.setPhone("+57" + ownerRequestDto.getPhone());
        }
        userServicePort.createOwner(userRequestMapper.ownerRequestDtoToUser(ownerRequestDto));
    }
    
    @Override
    public OwnerResponseDto getOwnerById(Long idOwner) {
        return userResponseMapper.userToOwnerResponseDto(userServicePort.getOwnerById(idOwner));
    }
    
    @Override
    public void createEmployee(EmployeeRequestDto employeeRequestDto, Long idRestaurant,
                               HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (employeeRequestDto.getPhone().length() <= 10 && employeeRequestDto.getPhone().contains("+57")) {
            throw new UserPhoneInvalidException();
        }
        if (employeeRequestDto.getPhone().length() == 10 && !employeeRequestDto.getPhone().contains("+57")) {
            employeeRequestDto.setPhone("+57" + employeeRequestDto.getPhone());
        }
        userServicePort.createEmployee(authHeader, userRequestMapper.employeeRequestDtoToUser(employeeRequestDto),
                                       idRestaurant);
        
    }
    
    @Override
    public void createClient(ClientRegisterRequestDto clientRegisterRequestDto) {
        if (clientRegisterRequestDto.getPhone().length() <= 10 && clientRegisterRequestDto.getPhone().contains("+57")) {
            throw new UserPhoneInvalidException();
        }
        if (clientRegisterRequestDto.getPhone().length() == 10 && !clientRegisterRequestDto.getPhone().contains("+57")) {
            clientRegisterRequestDto.setPhone("+57" + clientRegisterRequestDto.getPhone());
        }
        userServicePort.createClient(userRequestMapper.clientRegisterRequestDtoToUser(clientRegisterRequestDto));
    }
    
    
}
