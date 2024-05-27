package com.example.demo.controllers.item;

import com.example.demo.dtos.ItemTypeDTO;
import com.example.demo.services.tables.item.ItemTypeServiceJPA;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/item/type")
public class TypeController {
    @Autowired
    ItemTypeServiceJPA itemTypeServiceJPA;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItemType(ItemTypeDTO params) {
        Object result = itemTypeServiceJPA.getItemType(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createItemType(@Valid @RequestBody ItemTypeDTO params) {
        Object result = itemTypeServiceJPA.createNewType(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> editItemType(@PathVariable(value = "id") Long id,
                                               @RequestBody ItemTypeDTO params) {
        Object result = itemTypeServiceJPA.editItemType(id, params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> editItemType(@PathVariable(value = "id") Long id) {
        Object result = itemTypeServiceJPA.deleteItemType(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
