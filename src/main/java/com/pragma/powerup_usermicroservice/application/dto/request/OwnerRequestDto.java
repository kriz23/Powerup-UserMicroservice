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
public class OwnerRequestDto {
    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    
    @NotEmpty(message = "Surname is required")
    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    private String surname;
    
    @NotEmpty(message = "Document number is required")
    @Pattern(regexp = "^\\d{7,20}$", message = "Document number must be between 7 and 20 digits")
    private String docNumber;
    
    @NotEmpty(message = "Phone is required")
    @Pattern(regexp = "^\\+?\\d{12}$", message = "The phone is not valid")
    private String phone;
    
    @NotEmpty(message = "Birthdate is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Birthdate must be in format yyyy-MM-dd")
    private LocalDate birthdate;
    
    @NotEmpty(message = "Mail is required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "The mail is not valid")
    private String mail;
    
    @NotEmpty(message = "Password is required")
    private String password;
    
    @JsonIgnore
    private Long idRole;
}
