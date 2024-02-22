package com.pragma.powerup_usermicroservice.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    // DOMAIN EXCEPTIONS
    OWNER_MUST_BE_18_YO("The owner must be 18 years old"),
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