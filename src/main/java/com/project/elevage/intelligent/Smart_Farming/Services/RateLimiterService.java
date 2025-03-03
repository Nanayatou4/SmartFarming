package com.project.elevage.intelligent.Smart_Farming.Services;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {

    private final RateLimiter rateLimiter;

    public RateLimiterService() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(5)  // Max 5 requêtes
                .limitRefreshPeriod(Duration.ofSeconds(1))  // Reset chaque seconde
                .timeoutDuration(Duration.ZERO)  // Pas d'attente
                .build();

        this.rateLimiter = RateLimiter.of("default", config);
    }

    public boolean tryConsume() {
        return rateLimiter.acquirePermission();
    }
}




