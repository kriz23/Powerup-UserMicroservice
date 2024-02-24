package com.pragma.powerup_usermicroservice.application.handler;

import com.pragma.powerup_usermicroservice.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.request.OwnerRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.response.AdminResponseDto;
import com.pragma.powerup_usermicroservice.application.dto.response.LoggedUserResponseDto;
import com.pragma.powerup_usermicroservice.application.dto.response.OwnerResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface IUserHandler {
    LoggedUserResponseDto getLoggedUserByMail(String mail, HttpServletRequest request);
    AdminResponseDto getAdminByMail(String mail, HttpServletRequest request);
    OwnerResponseDto getOwnerByMail(String mail, HttpServletRequest request);
    void createOwner(OwnerRequestDto ownerRequestDto);
    OwnerResponseDto getOwnerById(Long idOwner);
    void createEmployee(EmployeeRequestDto employeeRequestDto, Long idRestaurant, HttpServletRequest request);
}
