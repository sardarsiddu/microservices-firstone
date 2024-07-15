package com.javagaide.kafkaproject.controller;

import com.javagaide.kafkaproject.Kafka.JsonKafkaProducer;
import com.javagaide.kafkaproject.Model.UserKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api/jsontype")
public class MessageJsonController {

    @Autowired
    private JsonKafkaProducer kafkaProducer;


    @PostMapping("/kafka")
    public ResponseEntity<String> pupblesher(@RequestBody UserKafka userKafka){
        kafkaProducer.sendMessage(userKafka);
        return ResponseEntity.ok("Json message sent to kafka Topic");
    }
}
