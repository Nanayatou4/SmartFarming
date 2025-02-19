package com.project.elevage.intelligent.Smart_Farming.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlertService {

    private final String THINGSBOARD_URL = "http://localhost:8080/api/plugins/telemetry/DEVICE/";
    private final String JWT_TOKEN = "YOUR_ACCESS_TOKEN"; // Remplace avec un token valide

    public boolean checkHealthAlert(String deviceId) {
        String url = THINGSBOARD_URL + deviceId + "/values/timeseries";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Authorization", "Bearer " + JWT_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Vérification des seuils critiques (ex: température trop élevée)
        String jsonData = response.getBody();
        return jsonData.contains("\"temperature\":40"); // Exemple seuil critique
    }
}
