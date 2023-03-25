package com.dungnguyen.exceptionmessagecustom.handler;

import com.dungnguyen.exceptionmessagecustom.handler.exceptions.RegisterException;
import com.dungnguyen.exceptionmessagecustom.handler.exceptions.UserNotFoundException;
import com.dungnguyen.exceptionmessagecustom.service.GeneralMessageAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;


@ControllerAdvice
public class AuthenticationExceptionHandler {
    @Autowired
    private GeneralMessageAccessor generalMessageAccessor;

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        final RestErrorResponse response = new RestErrorResponse(HttpStatus.BAD_REQUEST.value());
        response.addError(generalMessageAccessor.getMessage(null, "user.account.user_not_found",
                null));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RegisterException.class)
    ResponseEntity<Object> handleRegisterException(RegisterException exception) {
        final RestErrorResponse response = new RestErrorResponse(HttpStatus.BAD_REQUEST.value());
        response.addError(generalMessageAccessor.getMessage(null, exception.getErrorMessage(),
                null, null));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<Object> handleAuthenticationException(AuthenticationException exception) {
        final RestErrorResponse response = new RestErrorResponse(HttpStatus.UNAUTHORIZED.value());
        response.addError(generalMessageAccessor.getMessage(null, "user.account.username_or_password_not_match",
                null));

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
