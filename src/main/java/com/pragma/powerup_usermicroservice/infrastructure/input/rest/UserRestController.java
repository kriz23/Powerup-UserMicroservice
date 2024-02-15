package com.pragma.powerup_usermicroservice.infrastructure.input.rest;

import com.pragma.powerup_usermicroservice.application.dto.request.OwnerRequestDto;
import com.pragma.powerup_usermicroservice.application.dto.response.OwnerResponseDto;
import com.pragma.powerup_usermicroservice.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Rest Controller", description = "Rest controller for user operations")
@RestController
@RequestMapping("/api/v1/users/owners")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;
    
    @Operation(summary = "Create a new owner")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content),
            @ApiResponse(responseCode = "403", description = "Role not allowed for owner creation", content =
            @Content)})
    @PostMapping("/")
    public ResponseEntity<Void> createOwner(@RequestBody OwnerRequestDto ownerRequestDto) {
        ownerRequestDto.setIdRole(2L);
        userHandler.createOwner(ownerRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @Operation(summary = "Get desired owner by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Desired owner returned", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = OwnerResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> getOwner(@Parameter(description = "Owner id") @PathVariable Long id) {
        return ResponseEntity.ok(userHandler.getOwner(id));
    }
}
