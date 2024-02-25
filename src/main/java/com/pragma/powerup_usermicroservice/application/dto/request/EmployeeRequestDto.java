package com.pragma.powerup_usermicroservice.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequestDto {
    private String name;
    private String surname;
    private String docNumber;
    private String phone;
    private LocalDate birthdate;
    private String mail;
    private String password;
    @JsonIgnore
    private Long idRole;
}
