package com.pragma.powerup_usermicroservice.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientRegisterRequestDto {
    private String name;
    private String surname;
    private String docNumber;
    private String phone;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Birthdate must be in format yyyy-MM-dd")
    private LocalDate birthdate;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "The mail is not valid")
    private String mail;
    private String password;
    @JsonIgnore
    private Long idRole;
}
