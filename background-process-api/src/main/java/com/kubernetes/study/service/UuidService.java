package com.kubernetes.study.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UuidService {

    private static final Logger logger = LoggerFactory.getLogger(UuidService.class);

    private final RestClient restClient;

    public UuidService(@Value("${app.uuid-host}") String baseUrl) {
        logger.info("uuid-host {}", baseUrl);
        this.restClient = RestClient
                .builder()
                .baseUrl(baseUrl)
                .build();
    }

    public String getUniqueId() {
        logger.info("Call external service to generate a new unique ID");
        var uuid = restClient.get()
                .uri("/generate")
                .retrieve()
                .body(String.class);
        if (uuid == null || uuid.isBlank()) {
            logger.error("Error on call uuid-service");
            return null;
        }
        logger.info("ID {} received", uuid);
        return uuid;
    }


}
