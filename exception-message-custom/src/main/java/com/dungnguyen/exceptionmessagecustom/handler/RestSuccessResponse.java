package com.dungnguyen.exceptionmessagecustom.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestSuccessResponse<T> extends RestBaseResponse {
    private T result;
    private boolean success;

    public RestSuccessResponse(T result, boolean success) {
        super(success);
        this.result = result;
        this.success = success;
    }
}
