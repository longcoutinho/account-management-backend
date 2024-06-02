package com.example.demo.controllers;

import com.example.demo.services.topupgame.TopUpGameServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/report")
public class ReportController {
    @Autowired
    TopUpGameServiceJPA topUpServiceJPA;

//    @GetMapping(value = "/top-up", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> reportTopUp() {
//        Object result = topUpServiceJPA.report();
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
}
