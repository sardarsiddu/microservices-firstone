package com.javagaide.kafkaproject.Kafka;

import com.javagaide.kafkaproject.Model.UserKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {
    private KafkaTemplate<String, UserKafka> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, UserKafka> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserKafka datekafka){
        System.out.println(String.format("message sent JSon -> %s", datekafka.toString()));
        Message<UserKafka> message = MessageBuilder
                .withPayload(datekafka)
                .setHeader(KafkaHeaders.TOPIC,"javagaides_json")
                .build();
        kafkaTemplate.send(message);
    }
}
