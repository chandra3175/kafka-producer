package com.imoney.kafka.producer.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

	private Long id;
	private String name;
	private String rollNumber;

}
