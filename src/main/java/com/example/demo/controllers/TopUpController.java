package com.example.demo.controllers;

import com.example.demo.dtos.GetAllTopUpDTO;
import com.example.demo.dtos.TopUpRequestDTO;
import com.example.demo.dtos.topup.PaymentStatusRequestDTO;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.services.topupgame.TopUpGameServiceJPA;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/top-up")
public class TopUpController {
    @Autowired
    TopUpGameServiceJPA topUpServiceJPA;

    @PostMapping(value = "/confirm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> confirm(@RequestBody TopUpRequestDTO params) {
        Object result = topUpServiceJPA.confirm(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> cancel(@RequestBody TopUpRequestDTO params) {
        Object result = topUpServiceJPA.cancel(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll(GetAllTopUpDTO params, HttpServletRequest httpServletRequest) {
        UserEntity userEntity = (UserEntity) httpServletRequest.getAttribute("userInfo");
        if (userEntity.getRole().equals("USER")) {
            params.setUsername(userEntity.getUsername());
        }
        Object result = topUpServiceJPA.getAll(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPaymentStatus(PaymentStatusRequestDTO params) {
        Object result = topUpServiceJPA.getPaymentStatus(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
