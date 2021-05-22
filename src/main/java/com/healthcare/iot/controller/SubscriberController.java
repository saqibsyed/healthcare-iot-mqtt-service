package com.healthcare.iot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.healthcare.iot.entity.MqttSubscribedMsg;
import com.healthcare.iot.service.SubscriberImpl;

@RestController
public class SubscriberController {

	@Autowired
	SubscriberImpl subscriberImpl;

	private static final String topics[] = { "status_check", "Doctor_name" };

	@GetMapping("/getMessage")
	public MqttSubscribedMsg getMessage() {
		subscriberImpl.subscribe(topics);

		List<MqttSubscribedMsg> allMessages = subscriberImpl.getMessage();

		// MessageEntity entity = new MessageEntity();
		//System.out.println("messageReceived ==> " + allMessages.get(allMessages.size()-1));
		return !allMessages.isEmpty() ? allMessages.get(allMessages.size()-1) : null;
	}
}
