package com.example.demo.controllers.card;

import com.example.demo.services.tables.item.CardItemServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    CardItemServiceJPA cardItemServiceJPA;

    @GetMapping(value = "/card/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getByCardId(@PathVariable(value = "id") Long id) {
        Object result = cardItemServiceJPA.getItemByCardId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
