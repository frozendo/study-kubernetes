package com.kubernetes.study.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class GreetingApi {

    @GetMapping
    public String sayHello() {
        return "Hello World";
    }

}
