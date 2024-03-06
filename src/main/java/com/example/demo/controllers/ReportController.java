package com.example.demo.controllers;

import com.example.demo.dtos.GetAllTopUpDTO;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.services.tables.TopUpServiceJPA;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/report")
public class ReportController {
    @Autowired
    TopUpServiceJPA topUpServiceJPA;

//    @GetMapping(value = "/top-up", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> reportTopUp() {
//        Object result = topUpServiceJPA.report();
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
}
