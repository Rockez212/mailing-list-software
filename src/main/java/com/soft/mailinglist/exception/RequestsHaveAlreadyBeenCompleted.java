package com.soft.mailinglist.exception;

public class RequestsHaveAlreadyBeenCompleted extends RuntimeException {
    public RequestsHaveAlreadyBeenCompleted(String message) {
        super(message);
    }
}
