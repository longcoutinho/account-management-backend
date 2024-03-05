package com.example.demo.controllers;

import com.example.demo.kafka.KafkaProducer;
import com.example.demo.services.tables.UserServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    UserServiceJPA userServiceJPA;

    @Autowired
    KafkaProducer kafkaProducer;

    /**
     * API Health Check
     *
     * @return
     */
    @GetMapping(value = "/kafka", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> healthz() {
        String message = "longhvh";
        kafkaProducer.sendMessage(message);
        return new ResponseEntity<>(1L, HttpStatus.OK);
    }
}
