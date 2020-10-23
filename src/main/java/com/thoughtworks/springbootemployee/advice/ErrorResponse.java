package com.thoughtworks.springbootemployee.advice;

public class ErrorResponse {
    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    String message;
    String name;

    public ErrorResponse(String message, String name) {
        this.message = message;
        this.name = name;
    }
}
