package com.pragma.powerup_usermicroservice.infrastructure.input.rest;

import com.pragma.powerup_usermicroservice.application.dto.response.RoleResponseDto;
import com.pragma.powerup_usermicroservice.application.handler.IRoleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Role", description = "Role related endpoints")
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleRestController {
    private final IRoleHandler roleHandler;
    
    @Operation(summary = "Get desired role by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Desired role returned", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> getRole(@Parameter(description = "Role id") @PathVariable Long id) {
        return ResponseEntity.ok(roleHandler.getRole(id));
    }
    
    @Operation(summary = "Get all roles")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All roles returned", content =
    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation =
            RoleResponseDto.class)))), @ApiResponse(responseCode = "404", description = "No data found", content =
    @Content)})
    @GetMapping("/")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        return ResponseEntity.ok(roleHandler.getAllRoles());
    }
    
}
