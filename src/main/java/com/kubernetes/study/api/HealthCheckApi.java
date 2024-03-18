package com.kubernetes.study.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckApi {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckApi.class);

    private boolean readinessGood;

    public HealthCheckApi() throws InterruptedException {
        this.readinessGood = true;
        Thread.sleep(5000);
    }

    @GetMapping("/readiness")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> readiness() {
        logger.info("Run readiness probe");
        if (readinessGood) {
            logger.info("It's safe!! Readiness is good!!");
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        logger.info("Houston we have a problem!!");
        return new ResponseEntity<>("NOK", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/readiness/change")
    public String changeReadiness() {
        readinessGood = !readinessGood;
        return "OK";
    }

}
