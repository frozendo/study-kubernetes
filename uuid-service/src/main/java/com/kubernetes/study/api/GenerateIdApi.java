package com.kubernetes.study.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/generate")
public class GenerateIdApi {

    private static final Logger logger = LoggerFactory.getLogger(GenerateIdApi.class);

    @GetMapping
    public String generateNewId() {
        logger.info("Received request to generate a new id");
        var uuid = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(8);

        logger.info("ID {} generated", uuid);
        return uuid;
    }

}
