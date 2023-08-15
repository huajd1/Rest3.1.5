package com.example.spring.security.springsecurity.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalException {


    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(
            Exception exception) {
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
