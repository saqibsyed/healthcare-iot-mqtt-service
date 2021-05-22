package com.healthcare.iot.config;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfiguration{
	
	/** The broker url. */
	 private static final String brokerUrl = "tcp://127.0.0.1:1883";
	//private static final String brokerUrl = "tcp://192.168.179.55:1883";

	/** The client id. */
	private static final String clientId = "saqib_pc";

	/** The topic. */
	// private static final String topic = "topic";
	//private static final String topic = "status_check";
	
	@Bean
	public MqttClient mqttClient() throws MqttException {
		
		// MqttClient mqttClient = new MqttClient("tcp://" + hostname + ":" + port, clientId);
		System.out.println("Mqtt Connecting to broker: " + brokerUrl);
		MqttClient mqttClient = new MqttClient(brokerUrl, clientId);
		
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		mqttClient.connect(connOpts);
		System.out.println("Mqtt Connected");
		System.out.println("Subscribed");
		System.out.println("Listening");
		
		return mqttClient;
	}
}
