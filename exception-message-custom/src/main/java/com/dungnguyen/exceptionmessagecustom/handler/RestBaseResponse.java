package com.dungnguyen.exceptionmessagecustom.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestBaseResponse {
    private boolean success;

    public RestBaseResponse(boolean success) {
        this.success = success;
    }

}
