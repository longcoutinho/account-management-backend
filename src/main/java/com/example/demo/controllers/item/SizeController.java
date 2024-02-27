package com.example.demo.controllers.item;

import com.example.demo.dtos.ItemDTO;
import com.example.demo.dtos.SizeDTO;
import com.example.demo.services.tables.item.SizeServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/item/size")
public class SizeController {
    @Autowired
    SizeServiceJPA sizeServiceJPA;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSize(SizeDTO params) {
        Object result = sizeServiceJPA.getSize(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createSize(SizeDTO params) throws IOException {
        Object result = sizeServiceJPA.createNewSize(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
