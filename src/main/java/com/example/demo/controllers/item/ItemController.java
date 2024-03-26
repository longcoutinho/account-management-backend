package com.example.demo.controllers.item;

import com.example.demo.dtos.ItemDTO;
import com.example.demo.dtos.ItemTypeDTO;
import com.example.demo.services.tables.item.ItemServiceJPA;
import com.example.demo.services.tables.item.ItemTypeServiceJPA;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    ItemServiceJPA itemServiceJPA;

    @GetMapping(value = "/card/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getByCardId(@PathVariable(value = "id") Long id) {
        Object result = itemServiceJPA.getItemByCardId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
