package com.example.demo.controllers;

import com.example.demo.dtos.GetAllTopUpDTO;
import com.example.demo.dtos.TopUpRequestDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.services.tables.TopUpServiceJPA;
import com.example.demo.services.tables.UserServiceJPA;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/top-up")
public class TopUpController {
    @Autowired
    TopUpServiceJPA topUpServiceJPA;

    @PostMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> request(@Valid @RequestBody TopUpRequestDTO params,
                                          HttpServletRequest httpServletRequest) {
        UserEntity userEntity = (UserEntity) httpServletRequest.getAttribute("userInfo");
        params.setUsername(userEntity.getUsername());
        Object result = topUpServiceJPA.createRequest(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/confirm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> confirm(@RequestBody TopUpRequestDTO params) {
        Object result = topUpServiceJPA.confirm(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll(GetAllTopUpDTO params, HttpServletRequest httpServletRequest) {
        UserEntity userEntity = (UserEntity) httpServletRequest.getAttribute("userInfo");
        params.setUsername(userEntity.getUsername());
        Object result = topUpServiceJPA.getAll(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
