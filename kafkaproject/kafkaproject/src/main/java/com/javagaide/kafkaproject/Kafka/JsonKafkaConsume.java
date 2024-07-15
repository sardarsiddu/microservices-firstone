package com.javagaide.kafkaproject.Kafka;

import com.javagaide.kafkaproject.Model.UserKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class JsonKafkaConsume {

    @KafkaListener(topics = "javagaides_json",groupId = "mygroup")
    public void consumerkafka(UserKafka userKafka){
      System.out.println(String.format("Json message recevied -> %s", userKafka.toString()));
    }
}
