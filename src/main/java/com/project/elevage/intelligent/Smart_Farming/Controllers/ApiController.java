package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Services.RateLimiterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final RateLimiterService rateLimiterService;

    public ApiController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @GetMapping("/protected")
    public String getProtectedData() {
        if (!rateLimiterService.tryConsume()) {
            return "Trop de requêtes, veuillez patienter...";
        }
        return "Donnée sécurisée !";
    }
}
