package com.pragma.powerup_usermicroservice.infrastructure.input.rest;

import com.pragma.powerup_usermicroservice.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.request.OwnerRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.response.AdminResponseDto;
import com.pragma.powerup_usermicroservice.application.dto.response.LoggedUserResponseDto;
import com.pragma.powerup_usermicroservice.application.dto.response.OwnerResponseDto;
import com.pragma.powerup_usermicroservice.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;
    
    @Operation(summary = "Get users by mail")
    @Tag(name = "Logged", description = "Logged related endpoints")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Desired user returned", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = LoggedUserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)})
    @GetMapping("/mail/{mail}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIO', 'EMPLEADO', 'CLIENTE')")
    public ResponseEntity<LoggedUserResponseDto> getUserByMail(
            @Parameter(description = "User's mail") @PathVariable String mail, HttpServletRequest request) {
        return ResponseEntity.ok(userHandler.getLoggedUserByMail(mail, request));
    }
    
    @Operation(summary = "Get admin by mail")
    @Tag(name = "Admin", description = "Admin related endpoints")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Desired admin returned", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = AdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)})
    @GetMapping("/admins/mail/{mail}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminResponseDto> getAdminByMail(
            @Parameter(description = "Admin mail") @PathVariable String mail, HttpServletRequest request) {
        return ResponseEntity.ok(userHandler.getAdminByMail(mail, request));
    }
    
    @Operation(summary = "Create a new owner")
    @Tag(name = "Owner", description = "Owner related endpoints")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content),
            @ApiResponse(responseCode = "403", description = "Role not allowed for owner creation", content =
            @Content)})
    @PostMapping("/admins/owners")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createOwner(@RequestBody OwnerRequestDto ownerRequestDto) {
        ownerRequestDto.setIdRole(2L);
        userHandler.createOwner(ownerRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @Operation(summary = "Get desired owner by id", tags = "Owner")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Desired owner returned", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = OwnerResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)})
    @GetMapping("/admins/owners/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OwnerResponseDto> getOwnerById(@Parameter(description = "Owner id") @PathVariable Long id) {
        return ResponseEntity.ok(userHandler.getOwnerById(id));
    }
    
    @Operation(summary = "Get desired owner by mail", tags = "Owner")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Desired owner returned", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = OwnerResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)})
    @GetMapping("/admins/owners/mail/{mail}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OwnerResponseDto> getOwnerByMail(
            @Parameter(description = "Owner mail") @PathVariable String mail, HttpServletRequest request) {
        return ResponseEntity.ok(userHandler.getOwnerByMail(mail, request));
    }
    
    @Operation(summary = "Create a new employee")
    @Tag(name = "Employee", description = "Employee related endpoints")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Employee already exists", content = @Content),
            @ApiResponse(responseCode = "403", description = "Role not allowed for employee creation", content =
            @Content)})
    @PostMapping("/owners/employees/restaurants/{idRestaurant}")
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<Void> createEmployee(@Parameter(description = "Restaurant id") @PathVariable Long idRestaurant,
                                               @RequestBody EmployeeRequestDto employeeRequestDto,
                                               HttpServletRequest request) {
        employeeRequestDto.setIdRole(3L);
        userHandler.createEmployee(employeeRequestDto, idRestaurant, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
