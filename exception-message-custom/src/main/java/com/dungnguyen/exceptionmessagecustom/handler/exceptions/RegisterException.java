package com.dungnguyen.exceptionmessagecustom.handler.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterException extends RuntimeException {

    private final String errorMessage;

}
