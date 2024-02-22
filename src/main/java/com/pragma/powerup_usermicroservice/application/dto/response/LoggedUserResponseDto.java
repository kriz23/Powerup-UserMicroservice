package com.pragma.powerup_usermicroservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoggedUserResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String docNumber;
    private String phone;
    private LocalDate birthdate;
    private String mail;
    private String password;
    private RoleResponseDto role;
}
