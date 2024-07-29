package com.example.demo.controllers.card;

import com.example.demo.services.tables.item.CardItemServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/card/fee")
public class CardFeeController {
    @Autowired
    CardItemServiceJPA cardItemServiceJPA;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> get() {
        Object result = cardItemServiceJPA.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}