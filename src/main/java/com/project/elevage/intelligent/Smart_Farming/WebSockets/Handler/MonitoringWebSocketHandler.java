package com.project.elevage.intelligent.Smart_Farming.WebSockets.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.elevage.intelligent.Smart_Farming.Services.EmailService;
import com.project.elevage.intelligent.Smart_Farming.Services.SystemMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class MonitoringWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private EmailService  emailService;

    // Seuils d'alerte
    private static final double CPU_THRESHOLD = 90.0;
    private static final double MEMORY_THRESHOLD = 90.0;

    private final SystemMonitoringService monitoringService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public MonitoringWebSocketHandler(SystemMonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                double cpuUsage = monitoringService.getCpuUsage();
                double memoryUsage = monitoringService.getMemoryUsage();

                // Créer les données à envoyer par WebSocket
                Map<String, Object> usageData = Map.of(
                        "cpuUsage", cpuUsage,
                        "memoryUsage", memoryUsage
                );
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(usageData)));

                // Si l'utilisation CPU ou mémoire dépasse le seuil, envoyer un email
                if (cpuUsage > CPU_THRESHOLD) {
                    emailService.sendAlert("admin@exemple.com", "Alerte CPU", "L'utilisation du CPU a dépassé " + CPU_THRESHOLD + "% !");
                }

                if (memoryUsage > MEMORY_THRESHOLD) {
                    emailService.sendAlert("admin@exemple.com", "Alerte Mémoire", "L'utilisation de la mémoire a dépassé " + MEMORY_THRESHOLD + "% !");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 5, TimeUnit.SECONDS); // Envoi toutes les 5 secondes
    }
}