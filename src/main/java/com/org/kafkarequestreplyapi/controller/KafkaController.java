package com.org.kafkarequestreplyapi.controller;

import com.org.kafkarequestreplyapi.response.Record;
import com.org.kafkarequestreplyapi.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class KafkaController {

    @Autowired
    KafkaService kafkaService;

    @PostMapping("/get-result")
    public Mono<Object> getObject(@RequestBody Record record)
            throws Exception {
       return Mono.fromFuture(kafkaService.kafkaRequestReply(record));
    }
}
