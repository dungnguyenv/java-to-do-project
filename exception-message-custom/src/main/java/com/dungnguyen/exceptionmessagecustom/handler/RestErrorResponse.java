package com.dungnguyen.exceptionmessagecustom.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestErrorResponse extends RestBaseResponse {
    private String stackTrace;
    private final Integer errorCode;
    private List<String> errors;

    public RestErrorResponse(Integer errorCode) {
        super(false);
        this.errorCode = errorCode;
        this.setErrors(new ArrayList<>());
    }

    public RestErrorResponse(Integer errorCode, List<String> errors) {
        super(false);
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public RestErrorResponse(Integer errorCode, String message) {
        super(false);
        this.errorCode = errorCode;
        this.setErrors(new ArrayList<>());
        this.errors.add(message);
    }

    public void addError(String message) {
        this.errors.add(message);
    }
}