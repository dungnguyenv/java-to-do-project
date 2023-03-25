package com.dungnguyen.exceptionmessagecustom.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception-message")
public class ExceptionMessageController {

    @GetMapping("/bad-request")
    public void badRequestException(){
        throw new RuntimeException("request.bad-request-message");
    }

}
