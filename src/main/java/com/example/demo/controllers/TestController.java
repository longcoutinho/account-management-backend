package com.example.demo.controllers;

import com.example.demo.services.tables.UserServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    UserServiceJPA userServiceJPA;

    /**
     * API Health Check
     *
     * @return
     */
    @GetMapping(value = "/healthz", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> healthz() {
        Object result = userServiceJPA.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
