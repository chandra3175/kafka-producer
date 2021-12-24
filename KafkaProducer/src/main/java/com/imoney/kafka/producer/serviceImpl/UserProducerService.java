package com.imoney.kafka.producer.serviceImpl;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.imoney.kafkaclient.producer.impl.BaseProducer;
import com.imoney.kafkaclient.utill.KafkaUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserProducerService extends BaseProducer<String, String> {

	@Autowired
	private Gson gson;

	@Autowired
	private Environment environment;

	@PostConstruct
	public void init() {
		this.configure(KafkaUtil.getProducerProperties(environment));
	}

	@Override
	public void publish(String topic, Object payload) {
		String value = gson.toJson(payload);
		ProducerRecord<String, String> data = new ProducerRecord<>(topic, value);
		log.info(data.value());

		producer.send(data);
		producer.flush();

	} 
}
