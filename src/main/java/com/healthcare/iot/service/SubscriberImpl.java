package com.healthcare.iot.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.iot.entity.MqttSubscribedMsg;
import com.healthcare.iot.repository.MessageRepo;

@Service
public class SubscriberImpl implements MqttCallback {

	public String messageConsumed;

	public LinkedList<MqttSubscribedMsg> list = new LinkedList<>();
	//public LinkedList<String> list = new LinkedList<>();

	@Autowired
	private MqttClient mqttClient;
	
	@Autowired
	private MessageRepo messageRepo;

	public void subscribe(String[] topics) {
		// MemoryPersistence persistence = new MemoryPersistence();
		try {
			System.out.println(topics);
			mqttClient.setCallback(this);
			mqttClient.subscribe(topics);
			System.out.println("Listening after callBack");

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
		messageConsumed = message.toString();
		System.out.println(messageConsumed);
		MqttSubscribedMsg entity = new MqttSubscribedMsg();
		
		if(topic.equals("status_check")) {
		//sample sresponse => Device_status_25=Sanitized, Epoch=1621081041
		entity.setId(UUID.randomUUID().toString());
		entity.setDeviceId(messageConsumed.substring(14, 16));
		entity.setDeviceStatus(messageConsumed.substring(17, 26));
		LocalDateTime ldt = Instant.ofEpochSecond(Long.valueOf(messageConsumed.substring(34))).atZone(ZoneId.systemDefault()).toLocalDateTime();
		entity.setTimeStamp(ldt.toString());
		messageRepo.save(entity);
		}
		if(topic.equals("Doctor_name")) {
			entity.setDeviceUser(messageConsumed.toString());
		}
		list.add(entity);
	}

	public List<MqttSubscribedMsg> getMessage() {
		List<MqttSubscribedMsg> MqttSubMsgList = messageRepo.findAll();
		if (!MqttSubMsgList.isEmpty()) {
			return MqttSubMsgList;
		} else {
			return new ArrayList<>();
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
