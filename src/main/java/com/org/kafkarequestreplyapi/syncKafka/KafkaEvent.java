package com.org.kafkarequestreplyapi.syncKafka;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


public class KafkaEvent {
    @KafkaListener(groupId="${kafka.request-reply.consumer-group}", topics = "${kafka.request-reply.send-topics}")
    @SendTo
    public Message<?> listen(ConsumerRecord<String, Object> consumerRecord) {
        String reversedString = new StringBuilder( String.valueOf(consumerRecord.value()) ).toString();
        return MessageBuilder.withPayload( reversedString )
                .build();
    }

}
