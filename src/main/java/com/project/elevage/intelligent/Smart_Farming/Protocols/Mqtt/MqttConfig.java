package com.project.elevage.intelligent.Smart_Farming.Protocols.Mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {

    private static final String BROKER_URL = "tcp://localhost:1883"; // Remplace localhost par l'IP de ton broker si nécessaire
    private static final String CLIENT_ID = "smartFarmingClient";
    private static final String TOPIC = "sensor/data";

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{BROKER_URL});
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(CLIENT_ID, mqttClientFactory(), TOPIC);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            String payload = message.getPayload().toString();
            System.out.println("Message reçu via MQTT: " + payload);
        };
    }

    ///  RFID
    @Bean
    public MqttPahoMessageDrivenChannelAdapter rfidInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(CLIENT_ID, mqttClientFactory(), "rfid/data");
        adapter.setOutputChannel(rfidInputChannel());
        return adapter;
    }

    @Bean
    public MessageChannel rfidInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "rfidInputChannel")
    public MessageHandler rfidHandler() {
        return message -> {
            String payload = message.getPayload().toString();
            System.out.println("Badge RFID reçu via MQTT: " + payload);
        };
    }


}