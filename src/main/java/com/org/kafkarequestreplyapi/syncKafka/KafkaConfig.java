package com.org.kafkarequestreplyapi.syncKafka;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import java.time.Duration;

@Configuration
@Slf4j
public class KafkaConfig {


    @Value("${kafka.request-reply.reply-topics}")
    private String replyTopic;
    @Value("${kafka.request-reply.consumer-group}")
    private String consumerGroup;
    @Value("${kafka.request-reply.time-out}")
    private Integer timeOut = 5;



    @Bean //register and configure replying kafka template
    public ReplyingKafkaTemplate<String, String, String> replyingTemplate(
            ProducerFactory<String, String> pf,
            ConcurrentMessageListenerContainer<String, String> repliesContainer) {

        ReplyingKafkaTemplate<String,String, String> rTemplate = new ReplyingKafkaTemplate<>(pf, repliesContainer);
        rTemplate.setDefaultReplyTimeout(Duration.ofMinutes(timeOut));
        return rTemplate;

    }

    @Bean //register ConcurrentMessageListenerContainer bean
    public ConcurrentMessageListenerContainer<String, String> repliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {
        ConcurrentMessageListenerContainer<String, String> repliesContainer = containerFactory.createContainer(replyTopic);
        repliesContainer.getContainerProperties().setGroupId(consumerGroup);
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }
}
