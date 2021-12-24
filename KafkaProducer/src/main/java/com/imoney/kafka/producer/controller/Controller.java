package com.imoney.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imoney.kafka.producer.dto.User;
import com.imoney.kafka.producer.serviceImpl.UserProducerService;

@RestController
@RequestMapping("/test/kafka")
public class Controller {

	@Autowired
	private UserProducerService userProducerService;
	
	@Value("${kafka.user.details.topic}")
	public String topicName;
	
	@PostMapping("/produce/message")
	public String sendMessage(@RequestBody User payload) {

		try {
			userProducerService.publish(topicName, payload);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json message sent succuessfully";
	}
}
