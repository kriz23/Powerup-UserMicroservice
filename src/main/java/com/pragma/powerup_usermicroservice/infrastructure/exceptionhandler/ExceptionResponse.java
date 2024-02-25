package com.pragma.powerup_usermicroservice.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    // DOMAIN EXCEPTIONS
    OWNER_MUST_BE_18_YO("The owner must be 18 years old"),
    USER_NAME_INVALID("The user's name you entered is invalid or empty"),
    USER_SURNAME_INVALID("The user's surname you entered is invalid or empty"),
    USER_DOC_NUMBER_INVALID("The user's document number you entered is invalid or empty"),
    USER_PHONE_INVALID("The user's phone you entered is invalid or empty"),
    USER_BIRTHDATE_FORMAT_INVALID("The user's birthdate you entered is not in format yyyy-MM-dd or it is empty"),
    USER_MAIL_INVALID("The user's email you entered is invalid or empty"),
    RESTAURANT_OWNER_INVALID("You are not authorized to assign employees to this restaurant or it does not exists"),
    RESTAURANT_EMPLOYEE_ASSIGN_ERROR("An error occurred while assigning the employee to the restaurant"),
    // INFRASTRUCTURE EXCEPTIONS
    REQUEST_MAIL_INVALID("You are not authorized to get other user's information"),
    USER_ALREADY_EXISTS_DOC("A user with that document already exists"),
    MAIL_ALREADY_REGISTERED("A user with that email already exists");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}