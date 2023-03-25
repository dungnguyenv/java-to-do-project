package com.dungnguyen.exceptionmessagecustom.handler.exceptions;

public class NoSuchElementFoundException extends RuntimeException {

    public NoSuchElementFoundException(String message) {
        super(message);
    }

}