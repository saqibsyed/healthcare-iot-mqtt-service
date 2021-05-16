package com.iot.mqttdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.mqttdemo.entity.MessageEntity;
import com.iot.mqttdemo.service.SubscriberImpl;

@RestController
public class SubscriberController {

	@Autowired
	SubscriberImpl subscriberImpl;

	private static final String topic = "status_check";

	@GetMapping("/getMessage")
	public MessageEntity getMessage() {
		subscriberImpl.subscribe(topic);
		MessageEntity messageReceived = subscriberImpl.getMessage();
		//MessageEntity entity = new MessageEntity();
		System.out.println("messageReceived ==> " + messageReceived);
		return messageReceived;
	}
}
