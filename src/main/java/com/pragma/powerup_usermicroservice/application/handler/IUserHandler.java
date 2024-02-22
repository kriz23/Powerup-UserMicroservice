package com.pragma.powerup_usermicroservice.application.handler;

import com.pragma.powerup_usermicroservice.application.dto.request.OwnerRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.response.AdminResponseDto;
import com.pragma.powerup_usermicroservice.application.dto.response.LoggedUserResponseDto;
import com.pragma.powerup_usermicroservice.application.dto.response.OwnerResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface IUserHandler {
    void createOwner(OwnerRequestDto ownerRequestDto);
    OwnerResponseDto getOwnerById(Long idOwner);
    OwnerResponseDto getOwnerByMail(String mail, HttpServletRequest request);
    AdminResponseDto getAdminByMail(String mail, HttpServletRequest request);
    LoggedUserResponseDto getLoggedUserByMail(String mail, HttpServletRequest request);
}
