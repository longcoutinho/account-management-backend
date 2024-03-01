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

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItem(ItemDTO params) {
        Object result = itemServiceJPA.getItem(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDetailItem(@PathVariable(value = "id") Long id) {
        Object result = itemServiceJPA.getItemById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createItem(ItemDTO params) throws IOException {
        Object result = itemServiceJPA.createNewItem(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteItem(@PathVariable(value = "id") Long id) {
        Object result = itemServiceJPA.deleteItem(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteItem(@PathVariable(value = "id") Long id,
                                             ItemDTO params) {
        Object result = itemServiceJPA.editItem(id, params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
