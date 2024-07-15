package com.javagaide.kafkaproject.controller;

import com.javagaide.kafkaproject.Kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api/message")
public class MessageController {

    @Autowired
    private KafkaProducer kafkaProducer;

    //localhost:8080/Api/message/publish?message=hello fucking buddy
    @GetMapping("/publish")
    public ResponseEntity<String> publisher(@RequestParam("message") String message){
       kafkaProducer.sendMessage(message);
       return ResponseEntity.ok("message sent to topic");
    }
}
