package com.project.elevage.intelligent.Smart_Farming.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ThingsBoardService {

    private final String THINGSBOARD_URL = "http://localhost:8080/api/plugins/telemetry/DEVICE/";
    private final String JWT_TOKEN = "YOUR_ACCESS_TOKEN"; // Récupérer un token valide

    public String getAnimalData(String deviceId) {
        String url = THINGSBOARD_URL + deviceId + "/values/timeseries";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Authorization", "Bearer " + JWT_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}
