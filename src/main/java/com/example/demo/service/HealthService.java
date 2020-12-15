package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class HealthService {

    RestTemplate restTemplate = new RestTemplate();
    private int attempt;

    public void clearAttempt(){
        attempt = 0;
    }

    @Retryable(maxAttempts = 10, value = RuntimeException.class, backoff = @Backoff(delay = 500, multiplier = 2))
    public String getHealth() {
        attempt++;
        return restTemplate.getForObject("http://localhost:8090/health", String.class) + " attempt - " + attempt;
    }
    @Recover
    public String recover(){
        return "NOT OK";
    }
}
