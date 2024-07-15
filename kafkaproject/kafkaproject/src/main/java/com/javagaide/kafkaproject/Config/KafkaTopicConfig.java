package com.javagaide.kafkaproject.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic javagaidsTopic(){
        return TopicBuilder.name("javagaides")
                .build();
    }

    @Bean
    public NewTopic javagaidsJsonTopic(){
        return TopicBuilder.name("javagaides_json")
                .build();
    }
}
