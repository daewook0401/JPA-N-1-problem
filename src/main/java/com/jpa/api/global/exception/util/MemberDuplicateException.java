package com.jpa.api.global.exception.util;

public class MemberDuplicateException extends RuntimeException {
    public MemberDuplicateException(String message){
        super(message);
    }
}
