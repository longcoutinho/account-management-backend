package com.example.demo.controllers;

import com.example.demo.services.tables.FileServiceJPA;
import com.example.demo.services.topupgame.TopUpGameServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/image")
public class ImageController {
    @Autowired
    FileServiceJPA fileServiceJPA;

    @GetMapping(value = "/{imageId}")
    public ResponseEntity<Object> getImage(@PathVariable(value = "imageId") Long imageId) throws IOException {
        byte[] result = fileServiceJPA.getByteFileById(imageId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
