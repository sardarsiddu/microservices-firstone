package com.javagaide.kafkaproject.Kafka;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service

public class KafkaProducer {

 //  private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(KafkaProducer.class);


    private KafkaTemplate<String, String> KafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.KafkaTemplate=kafkaTemplate;
    }

    public void sendMessage(String message){
      //  LOGGER.info(String.format("message sent %s",message));
        KafkaTemplate.send("javagaides" ,message);
    }
}
