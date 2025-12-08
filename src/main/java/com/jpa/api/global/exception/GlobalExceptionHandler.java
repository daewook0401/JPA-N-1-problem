package com.jpa.api.global.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jpa.api.global.exception.util.MemberDuplicateException;
import com.jpa.api.global.exception.util.MemberNotFoundException;
import com.jpa.api.global.exception.util.TeamNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<Map<String, String>> makeResponseEntity(RuntimeException e, HttpStatus status){
		Map<String, String> error = new HashMap<>();
		error.put("error-message", e.getMessage());
		return ResponseEntity.status(status).body(error);
	}

    @ExceptionHandler(MemberDuplicateException.class)
    public ResponseEntity<Map<String, String>> handleMemberIdDuplicateException(MemberDuplicateException e){
        return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMemberNotFoundException(MemberNotFoundException e){
        return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTeamNotFountException(TeamNotFoundException e){
        return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
    }

}