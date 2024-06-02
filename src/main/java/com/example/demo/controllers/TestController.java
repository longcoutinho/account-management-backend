package com.example.demo.controllers;

import com.example.demo.dtos.RequestBuyCardDTO;
import com.example.demo.services.payment.TripleAService;
import com.example.demo.payment.VNPayPayment;
import com.example.demo.services.shopcard.AppotaPayService;
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

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> test() throws ServletException, IOException {
        RequestBuyCardDTO request = new RequestBuyCardDTO();
        request.setTransactionId("1");
        request.setQuantity("1");
        request.setProductCode("VTT10");
        return new ResponseEntity<>(appotaPayService.buyCard(request), HttpStatus.OK);
    }
}
