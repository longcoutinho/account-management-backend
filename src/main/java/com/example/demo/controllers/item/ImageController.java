package com.example.demo.controllers.item;

import com.example.demo.services.tables.item.ImageServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/image")
public class ImageController {
    @Autowired
    ImageServiceJPA imageServiceJPA;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable(value = "id") Long id) throws IOException {
        return imageServiceJPA.getImageById(id);
    }
}
