package com.example.demo.controllers;

import com.example.demo.services.tables.CardServiceJPA;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/card")
public class CardController {
    @Autowired
    CardServiceJPA cardServiceJPA;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll() throws ServletException, IOException {
        return new ResponseEntity<>(cardServiceJPA.getAll(), HttpStatus.OK);
    }
}
