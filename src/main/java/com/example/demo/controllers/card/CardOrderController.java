package com.example.demo.controllers.card;

import com.example.demo.dtos.CardDTO;
import com.example.demo.services.tables.item.CardItemServiceJPA;
import com.example.demo.services.tables.item.CardOrderServiceJPA;
import com.example.demo.utils.constants.FnCommon;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card/order")
public class CardOrderController {
    @Autowired
    CardOrderServiceJPA cardOrderServiceJPA;

    /**
     * Mua the
     * @param params
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody CardDTO params) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
