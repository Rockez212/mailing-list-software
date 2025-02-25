package com.soft.mailinglist.exception;

public class UsernameOrEmailExistException extends RuntimeException {
    public UsernameOrEmailExistException(String message) {
        super(message);
    }
}
