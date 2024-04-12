package com.kubernetes.study.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/generate")
public class GenerateIdApi {

    @GetMapping
    public String generateNewId() {
        return  UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(8);
    }

}
