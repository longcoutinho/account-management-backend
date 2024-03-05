package com.example.demo.controllers.item;

import com.example.demo.dtos.SizeDTO;
import com.example.demo.dtos.StockAccountDTO;
import com.example.demo.services.tables.StockAccountServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/stock-account")
public class StockAccountController {
    @Autowired
    StockAccountServiceJPA stockAccountServiceJPA;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createSize(StockAccountDTO params) {
        Object result = stockAccountServiceJPA.createNewAccount(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
