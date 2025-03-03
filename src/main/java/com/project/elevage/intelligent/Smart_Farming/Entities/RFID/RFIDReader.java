package com.project.elevage.intelligent.Smart_Farming.Entities.RFID;
import com.fazecast.jSerialComm.SerialPort;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RFIDReader {
    public static void envoyerTagAuServeur(String tag) {
        try {
            String url = "http://localhost:8080/api/rfid"; // L'URL de ton API Spring Boot
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Corps de la requête JSON
            String jsonInputString = "{\"tag\": \"" + tag + "\"}";

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            System.out.println("Réponse du serveur : " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

