package com.soft.mailinglist.controller;

import com.soft.mailinglist.exception.InvalidPasswordException;
import com.soft.mailinglist.exception.RequestsHaveAlreadyBeenCompleted;
import com.soft.mailinglist.exception.UserNotFoundException;
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

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(RequestsHaveAlreadyBeenCompleted.class)
    public ResponseEntity<String> handleRequestsHaveAlreadyBeenCompleted(RequestsHaveAlreadyBeenCompleted e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
