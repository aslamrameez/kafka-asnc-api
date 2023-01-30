package com.org.kafkarequestreplyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaRequestReplyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaRequestReplyApiApplication.class, args);
	}

}
