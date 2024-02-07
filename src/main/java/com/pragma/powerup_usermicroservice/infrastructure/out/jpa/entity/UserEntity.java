package com.pragma.powerup_usermicroservice.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(unique = true, nullable = false, length = 20)
    private String docNumber;
    private String phone;
    private LocalDate birthdate;
    private String mail;
    private String password;
    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private RoleEntity roleEntity;
    
}
