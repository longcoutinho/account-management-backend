package com.example.demo.controllers;

import com.example.demo.dtos.TopUpRequestDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.services.tables.TopUpServiceJPA;
import com.example.demo.services.tables.UserServiceJPA;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/top-up")
public class TopUpController {
    @Autowired
    TopUpServiceJPA topUpServiceJPA;


    @PostMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> request(@Valid @RequestBody TopUpRequestDTO params) {
        Object result = topUpServiceJPA.createRequest(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/confirm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> confirm(@RequestBody TopUpRequestDTO params) {
        Object result = topUpServiceJPA.confirm(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
