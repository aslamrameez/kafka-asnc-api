package com.org.kafkarequestreplyapi.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import com.org.kafkarequestreplyapi.response.Record;

@Service
@Slf4j
public class KafkaService {

    @Autowired
    private ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;
    @Value("${kafka.request-reply.send-topics}")
    private String sendTopic;

    public CompletableFuture<SendResult<String, String>> kafkaRequestReply(Record record) throws Exception {
        ProducerRecord<String, String> req = new ProducerRecord<>(sendTopic, null, record.getKey(), record.getValue());
        return replyingKafkaTemplate.sendAndReceive(req).getSendFuture();
    }
}
