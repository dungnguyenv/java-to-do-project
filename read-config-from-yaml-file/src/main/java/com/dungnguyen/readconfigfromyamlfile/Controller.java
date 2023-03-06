package com.dungnguyen.readconfigfromyamlfile;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final MyAppProperties properties;

    @GetMapping("/demo")
    public String demo(){
        return properties.toString();
    }
}
