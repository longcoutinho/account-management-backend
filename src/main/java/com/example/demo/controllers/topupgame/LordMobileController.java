package com.example.demo.controllers.topupgame;

import com.example.demo.services.topupgame.LordMobileService;
import jakarta.servlet.ServletException;
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
@RequestMapping(value = "/lord-mobile")
public class LordMobileController {
    @Autowired
    LordMobileService lordMobileService;

    @GetMapping(value = "/search/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll(@PathVariable(value = "username") String username) throws ServletException, IOException {
        return new ResponseEntity<>(lordMobileService.getUsernameById(username), HttpStatus.OK);
    }
}
