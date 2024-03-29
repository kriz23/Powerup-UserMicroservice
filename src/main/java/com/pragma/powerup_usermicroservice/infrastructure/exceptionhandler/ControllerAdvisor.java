package com.pragma.powerup_usermicroservice.infrastructure.exceptionhandler;

import com.pragma.powerup_usermicroservice.domain.exception.*;
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
    
    @ExceptionHandler(UserNameInvalidException.class)
    public ResponseEntity<Map<String, String>> handleUserNameInvalidException(
            UserNameInvalidException ignoredUserNameInvalidException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.USER_NAME_INVALID.getMessage()));
    }
    
    @ExceptionHandler(UserSurnameInvalidException.class)
    public ResponseEntity<Map<String, String>> handleUserSurnameInvalidException(
            UserSurnameInvalidException ignoredUserSurnameInvalidException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.USER_SURNAME_INVALID.getMessage()));
    }
    
    @ExceptionHandler(UserDocNumberInvalidException.class)
    public ResponseEntity<Map<String, String>> handleUserDocNumberInvalidException(
            UserDocNumberInvalidException ignoredUserDocNumberInvalidException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.USER_DOC_NUMBER_INVALID.getMessage()));
    }
    
    @ExceptionHandler(UserPhoneInvalidException.class)
    public ResponseEntity<Map<String, String>> handleUserPhoneInvalidException(
            UserPhoneInvalidException ignoredUserPhoneInvalidException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.USER_PHONE_INVALID.getMessage()));
    }
    
    @ExceptionHandler(UserBirthdateFormatInvalidException.class)
    public ResponseEntity<Map<String, String>> handleUserBirthdateFormatInvalidException(
            UserBirthdateFormatInvalidException ignoredUserBirthdateFormatInvalidException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.USER_BIRTHDATE_FORMAT_INVALID.getMessage()));
    }
    
    @ExceptionHandler(UserMailInvalidException.class)
    public ResponseEntity<Map<String, String>> handleUserMailInvalidException(
            UserMailInvalidException ignoredUserMailInvalidException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.USER_MAIL_INVALID.getMessage()));
    }
    
    @ExceptionHandler(RestaurantOwnerInvalidException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantOwnerInvalidException(
            RestaurantOwnerInvalidException ignoredRestaurantOwnerInvalidException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.RESTAURANT_OWNER_INVALID.getMessage()));
    }
    
    @ExceptionHandler(RestaurantEmployeeAssignErrorException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantEmployeeAssignErrorException(
            RestaurantEmployeeAssignErrorException ignoredRestaurantEmployeeAssignErrorException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Collections.singletonMap(MESSAGE, ExceptionResponse.RESTAURANT_EMPLOYEE_ASSIGN_ERROR.getMessage()));
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