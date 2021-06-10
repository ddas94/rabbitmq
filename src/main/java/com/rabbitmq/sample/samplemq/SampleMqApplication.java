package com.rabbitmq.sample.samplemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.rabbitmq")
public class SampleMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleMqApplication.class, args);
	}

}
