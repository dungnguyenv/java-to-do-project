package com.dungnguyen.exceptionmessagecustom.handler.exceptions;


/**
 * This exception is thrown in case of a not activated user trying to
 * authenticate.
 */
public class UserNotActivatedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }
}