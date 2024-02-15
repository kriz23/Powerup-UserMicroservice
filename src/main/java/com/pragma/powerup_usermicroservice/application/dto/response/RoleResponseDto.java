package com.pragma.powerup_usermicroservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleResponseDto {
    private Long id;
    private String name;
    private String description;
}
