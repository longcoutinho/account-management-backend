package com.example.demo.controllers.item;

import com.example.demo.dtos.ItemDTO;
import com.example.demo.dtos.SaleOrderDTO;
import com.example.demo.repositories.tables.SaleOrderRepositoryJPA;
import com.example.demo.services.tables.SaleOrderServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sale-order")
public class SaleOrderController {
    @Autowired
    SaleOrderServiceJPA saleOrderServiceJPA;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(SaleOrderDTO params) {
        Object result = saleOrderServiceJPA.create(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItem(SaleOrderDTO params) {
        Object result = saleOrderServiceJPA.getAll(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
