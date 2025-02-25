package com.soft.mailinglist.controller;

import com.soft.mailinglist.exception.UsernameOrEmailExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleController {

    @ExceptionHandler(UsernameOrEmailExistException.class)
    public ResponseEntity<String> handleAmountMustBeGreaterThanZeroException(UsernameOrEmailExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
