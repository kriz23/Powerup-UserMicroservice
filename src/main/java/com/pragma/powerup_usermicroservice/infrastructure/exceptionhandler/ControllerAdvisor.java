package com.pragma.powerup_usermicroservice.infrastructure.exceptionhandler;

import com.pragma.powerup_usermicroservice.domain.exception.OwnerMustBe18yo;
import com.pragma.powerup_usermicroservice.infrastructure.exception.MailAlreadyRegistered;
import com.pragma.powerup_usermicroservice.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup_usermicroservice.infrastructure.exception.RequestMailInvalidException;
import com.pragma.powerup_usermicroservice.infrastructure.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    
    private static final String MESSAGE = "message";
    
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }
    
    // DOMAIN EXCEPTIONS
    
    @ExceptionHandler(OwnerMustBe18yo.class)
    public ResponseEntity<Map<String, String>> handleOwnerMustBe18yo(OwnerMustBe18yo ignoredOwnerMustBe18yo) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.OWNER_MUST_BE_18_YO.getMessage()));
    }
    
    // INFRASTRUCTURE EXCEPTIONS
    
    @ExceptionHandler(RequestMailInvalidException.class)
    public ResponseEntity<Map<String, String>> handleRequestMailInvalidException(
            RequestMailInvalidException ignoredRequestMailInvalidException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.REQUEST_MAIL_INVALID.getMessage()));
    }
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(
            UserAlreadyExistsException ignoredUserAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.USER_ALREADY_EXISTS_DOC.getMessage()));
    }
    
    @ExceptionHandler(MailAlreadyRegistered.class)
    public ResponseEntity<Map<String, String>> handleMailAlreadyRegistered(
            MailAlreadyRegistered ignoredMailAlreadyRegistered) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.MAIL_ALREADY_REGISTERED.getMessage()));
    }
    
    
}