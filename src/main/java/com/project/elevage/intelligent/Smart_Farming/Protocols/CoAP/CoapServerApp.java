package com.project.elevage.intelligent.Smart_Farming.Protocols.CoAP;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.elements.config.Configuration;
import org.springframework.stereotype.Component;

@Component
public class CoapServerApp extends CoapServer {

    public CoapServerApp() {
        super(new Configuration()); // Initialisation correcte du serveur CoAP
        add(new SensorResource());
        add(new RFIDResource()); // Ajout de la ressource RFID
    }

//    @PreDestroy
//    public void stopServer() {
//        this.stop();
//        System.out.println("CoAP Server arrêté !");
//    }

    @PostConstruct
    public void startServer() {
        this.start();
        System.out.println("CoAP Server démarré sur le port 5683");
    }

    static class SensorResource extends CoapResource {
        public SensorResource() {
            super("sensor");
        }

        @Override
        public void handlePOST(CoapExchange exchange) {
            String request = exchange.getRequestText();
            System.out.println("Données reçues via CoAP: " + request);
            exchange.respond("Données reçues via CoAP !");
        }
    }

    static class RFIDResource extends CoapResource {
        public RFIDResource() {
            super("rfid");
        }

        @Override
        public void handlePOST(CoapExchange exchange) {
            String request = exchange.getRequestText();
            System.out.println("Badge RFID reçu via CoAP: " + request);
            exchange.respond("Données RFID reçues via CoAP !");
        }
    }

}