package com.swdeisgn.course.hw4.auth.exceptions;

public class ClientErrorException extends RuntimeException {
    public ClientErrorException(String message) {
        super(message);
    }
}
