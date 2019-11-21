package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(value = NoConnectionException.class)
    public ResponseEntity<Object> exception(NoConnectionException exception) {
        return new ResponseEntity<>("Internet connection fail, please check it!",
                HttpStatus.NOT_FOUND);
    }
}
