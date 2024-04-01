package com.example.demo.controllers;

import com.example.demo.services.payment.TripleAService;
import com.example.demo.payment.VNPayPayment;
import com.example.demo.services.tables.TopUpGameServiceJPA;
import com.example.demo.services.tables.UserServiceJPA;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    UserServiceJPA userServiceJPA;

    @Autowired
    TripleAService tripleAPayment;

    @Autowired
    VNPayPayment vnPayPayment;

    @Autowired
    TopUpGameServiceJPA topUpGameServiceJPA;

    /**
     * API Health Check
     *
     * @return
     */
    @GetMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> healthz(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        return new ResponseEntity<>(vnPayPayment.doPost(httpServletRequest), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> test() throws ServletException, IOException {
        return new ResponseEntity<>(tripleAPayment.getAccessToken(), HttpStatus.OK);
    }

    @GetMapping(value = "/lord-mobile/send-otp/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> request(@PathVariable(value = "id") String id) {
        Object result = topUpGameServiceJPA.sendOtpLordMobile(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
