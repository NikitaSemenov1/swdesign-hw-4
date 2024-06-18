package com.swdesign.course.hw4.ticket.exceptions;

public class ClientErrorException extends RuntimeException {
    public ClientErrorException(String message) {
        super(message);
    }
}
