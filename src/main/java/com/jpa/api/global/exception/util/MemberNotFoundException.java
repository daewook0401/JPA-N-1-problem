package com.jpa.api.global.exception.util;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String message){
        super(message);
    }
}
