package com.example.demo.controllers.item;

import com.example.demo.dtos.ItemTypeDTO;
import com.example.demo.dtos.SizeDTO;
import com.example.demo.dtos.StockAccountDTO;
import com.example.demo.services.tables.StockAccountServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/stock-account")
public class StockAccountController {
    @Autowired
    StockAccountServiceJPA stockAccountServiceJPA;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createSize(@RequestBody StockAccountDTO params) {
        Object result = stockAccountServiceJPA.createNewAccount(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll(StockAccountDTO params) {
        Object result = stockAccountServiceJPA.getAll(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
