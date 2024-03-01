package com.example.demo.controllers.item;

import com.example.demo.dtos.SizeDTO;
import com.example.demo.repositories.tables.ColorRepositoryJPA;
import com.example.demo.services.tables.item.ColorServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/item/color")
public class ColorController {
    @Autowired
    ColorServiceJPA colorServiceJPA;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getColor(SizeDTO params) {
        Object result = colorServiceJPA.getColor(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createSize(SizeDTO params) throws IOException {
        Object result = colorServiceJPA.createNewColor(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
