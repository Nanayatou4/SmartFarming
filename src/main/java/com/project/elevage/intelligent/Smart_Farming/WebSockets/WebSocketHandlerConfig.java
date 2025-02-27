package com.project.elevage.intelligent.Smart_Farming.WebSockets;

import com.project.elevage.intelligent.Smart_Farming.WebSockets.Handler.MonitoringWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketHandlerConfig implements WebSocketConfigurer {

    private final MonitoringWebSocketHandler monitoringWebSocketHandler;

    public WebSocketHandlerConfig(MonitoringWebSocketHandler monitoringWebSocketHandler) {
        this.monitoringWebSocketHandler = monitoringWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(monitoringWebSocketHandler, "/ws/monitoring").setAllowedOrigins("*");
    }
}
