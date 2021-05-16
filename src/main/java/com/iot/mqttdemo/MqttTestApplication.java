package com.iot.mqttdemo;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MqttTestApplication {
	
	/** The broker url. */
	//private static final String brokerUrl = "tcp://127.0.0.1:1883";
	private static final String brokerUrl = "tcp://192.168.179.55:1883";

	/** The client id. */
	private static final String clientId = "JavaSample";

	/** The topic. */
	//private static final String topic = "topic";
	private static final String topic = "status_check";


	public static void main(String[] args) {
		SpringApplication.run(MqttTestApplication.class, args);
	}

	@Bean
	public MqttClient mqttClient(
			// @Value("${mqtt.clientId}") String clientId, 
			// @Value("${mqtt.hostname}") String hostname,
			// @Value("${mqtt.port}") int port
			
			) throws MqttException {

		//MqttClient mqttClient = new MqttClient("tcp://" + hostname + ":" + port, clientId);
		MqttClient mqttClient = new MqttClient(brokerUrl, clientId);

		//mqttClient.connect(mqttConnectOptions());
		
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		mqttClient.connect(connOpts);

		return mqttClient;
	}

	@Bean
	@ConfigurationProperties(prefix = "mqtt")
	public MqttConnectOptions mqttConnectOptions() {
		return new MqttConnectOptions();
	}
}
