package com.example.demo.controllers;

import com.example.demo.controllers.card.RequestCardInfoDTO;
import com.example.demo.dtos.RequestBuyCardDTO;
import com.example.demo.repositories.tables.entities.PaymentEntity;
import com.example.demo.services.payment.StripeService;
import com.example.demo.services.shopcard.AppotaPayService;
import com.example.demo.services.tables.CardOrderServiceJPA;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    AppotaPayService appotaPayService;

    @Autowired
    StripeService stripeService;

    @Autowired
    CardOrderServiceJPA cardOrderServiceJPA;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> test() throws Exception {
        return new ResponseEntity<>(cardOrderServiceJPA.getInfo(new RequestCardInfoDTO("971fb522-6ba7-4325-a37b-8b20c9aa2b01"), "maiphg31"), HttpStatus.OK);
    }

    @GetMapping(value = "/stripe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> stripetest() {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPrice(20000L);
        System.out.println(stripeService.createPayment(paymentEntity));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
