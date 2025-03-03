package com.project.elevage.intelligent.Smart_Farming.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    /// HTTP

    @PostMapping("/data")
    public String receiveSensorData(@RequestBody String data) {
        System.out.println("Données reçues via HTTP: " + data);
        return "Données reçues avec succès";
    }

    @PostMapping("/rfid")
    public String receiveRFID(@RequestBody String rfidData) {
        System.out.println("Badge RFID reçu via HTTP: " + rfidData);
        return "Données RFID reçues avec succès";
    }

}