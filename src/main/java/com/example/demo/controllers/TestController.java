package com.example.demo.controllers;

import com.example.demo.dtos.RequestBuyCardDTO;
import com.example.demo.repositories.tables.entities.PaymentEntity;
import com.example.demo.services.payment.StripeService;
import com.example.demo.services.payment.TripleAService;
import com.example.demo.payment.VNPayPayment;
import com.example.demo.services.shopcard.AppotaPayService;
import com.example.demo.services.tables.item.CardOrderServiceJPA;
import com.example.demo.services.topupgame.LordMobileService;
import com.example.demo.services.topupgame.TopUpGameServiceJPA;
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
    AppotaPayService appotaPayService;

    @Autowired
    StripeService stripeService;

    @Autowired
    CardOrderServiceJPA cardOrderServiceJPA;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> test() throws ServletException, IOException {
        RequestBuyCardDTO request = new RequestBuyCardDTO();
        request.setPartnerRefId("1");
        request.setQuantity("1");
        request.setProductCode("VTT10");
        return new ResponseEntity<>(appotaPayService.buyCard(request), HttpStatus.OK);
    }

    @GetMapping(value = "/stripe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> stripetest() {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPrice(20000L);
        System.out.println(stripeService.createPayment(paymentEntity));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getHistory() {
        return new ResponseEntity<>(cardOrderServiceJPA.getAll("maiphg31"), HttpStatus.OK);
    }
}
