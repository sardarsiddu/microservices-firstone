package com.javagaide.kafkaproject.Kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.slf4j.*;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {



    @KafkaListener(topics = "javagaides_json",groupId = "mygroup")
    public void consume(String message){
    System.out.println(String.format("message received  -> %s",message));
    }
}
