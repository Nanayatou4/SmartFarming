package com.project.elevage.intelligent.Smart_Farming.WebSockets.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.elevage.intelligent.Smart_Farming.Services.SystemMonitoringService;
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
                Map<String, Object> usageData = Map.of(
                        "cpuUsage", monitoringService.getCpuUsage(),
                        "memoryUsage", monitoringService.getMemoryUsage()
                );
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(usageData)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 5, TimeUnit.SECONDS); // Envoi toutes les 5 secondes
    }
}