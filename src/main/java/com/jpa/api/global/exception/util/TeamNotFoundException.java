package com.jpa.api.global.exception.util;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(String message){
        super(message);
    }
}
