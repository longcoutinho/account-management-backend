package com.example.demo.controllers;

import com.example.demo.dtos.saleorder.RequestProcessOrderDTO;
import com.example.demo.dtos.saleorder.RequestCardDTO;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.services.tables.SaleOrderServiceJPA;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/sale-order")
public class SaleOrderController {
    @Autowired
    SaleOrderServiceJPA saleOrderServiceJPA;

//     @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<Object> create(@RequestBody RequestCardDTO params, HttpServletRequest httpServletRequest) throws IOException {
//         UserEntity userEntity = (UserEntity) httpServletRequest.getAttribute("userInfo");
//         if (userEntity.getRole().equals("USER")) {
// //            params.setCreateUser(userEntity.getUsername());
//         }
//         Object result = saleOrderServiceJPA.create(params);
//         return new ResponseEntity<>(result, HttpStatus.OK);
//     }

//     @PostMapping(value = "/process", produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<Object> process(RequestProcessOrderDTO params, HttpServletRequest httpServletRequest) throws JsonProcessingException {
//         UserEntity userEntity = (UserEntity) httpServletRequest.getAttribute("userInfo");
//         if (userEntity.getRole().equals("USER")) {
//             params.setCreateUser(userEntity.getUsername());
//         }
//         Object result = saleOrderServiceJPA.processOrder(params);
//         return new ResponseEntity<>(result, HttpStatus.OK);
//     }

//     @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<Object> getItem(RequestCardDTO params) {
//         Object result = saleOrderServiceJPA.getAll(params);
//         return new ResponseEntity<>(result, HttpStatus.OK);
//     }
}
