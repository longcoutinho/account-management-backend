package com.example.demo.controllers.payment;

import com.example.demo.services.tables.PaymentMethodServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
    @Autowired
    PaymentMethodServiceJPA paymentMethodServiceJPA;

    @GetMapping(value = "/method", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListMethod() {
        Object result = paymentMethodServiceJPA.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
