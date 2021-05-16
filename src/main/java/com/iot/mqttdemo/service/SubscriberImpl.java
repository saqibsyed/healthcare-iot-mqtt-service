package com.iot.mqttdemo.service;

import java.util.LinkedList;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.mqttdemo.entity.MessageEntity;

@Service
public class SubscriberImpl implements MqttCallback {
	/** The broker url. */
	private static final String brokerUrl = "tcp://127.0.0.1:1883";

	/** The client id. */
	private static final String clientId = "JavaSample";

	/** The topic. */
	private static final String topic = "topic";

	public String messageReceived;

	public LinkedList<MessageEntity> list = new LinkedList<>();

	@Autowired
	private MqttClient mqttClient;

	/*
	 * public static void main(String[] args) {
	 * 
	 * new SubscriberImpl().subscribe(topic); }
	 */

	public void subscribe(String topic) {
		MemoryPersistence persistence = new MemoryPersistence();
		try {

			// MqttClient sampleClient = new MqttClient(brokerUrl, clientId, persistence);
			// MqttConnectOptions connOpts = new MqttConnectOptions();
			// connOpts.setCleanSession(true);

			System.out.println("checking");

			//System.out.println("Mqtt Connecting to broker: " + brokerUrl);
			// sampleClient.connect(connOpts);
			System.out.println("Mqtt Connected");

			// sampleClient.setCallback(this);
			// sampleClient.subscribe(topic);

			mqttClient.setCallback(this);
			mqttClient.subscribe(topic);

			System.out.println("Subscribed");
			System.out.println("Listening");

		} catch (MqttException me) {
			System.out.println("Mqtt reason " + me.getReasonCode());
			System.out.println("Mqtt msg " + me.getMessage());
			System.out.println("Mqtt loc " + me.getLocalizedMessage());
			System.out.println("Mqtt cause " + me.getCause());
			System.out.println("Mqtt excep " + me);
		}
	}

	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("Mqtt topic : " + topic);
		System.out.println("Mqtt msg : " + message.toString());
		// messageReceived = message.toString();
		MessageEntity entity = new MessageEntity();
		entity.setDocStatus(true);
		entity.setEpochTime(System.currentTimeMillis());
		entity.setMsg(message.toString());
		list.add(entity);
	}

	public MessageEntity getMessage() {
		if (list.size() > 0) {
			return list.getLast();
		} else {
			return new MessageEntity();
		}
	}

	/*-
	public void subscribefinal String topic) throws MqttException, InterruptedException {
		System.out.println("Messages received:");
	
		mqttClient.subscribeWithResponse(topic, (tpic, msg) -> {
			System.out.println(msg.getId() + " -> " + new String(msg.getPayload()));
		});
	
	}
	
	*/
}
