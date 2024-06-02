package com.example.demo.controllers.topupgame;

import com.example.demo.services.topupgame.TopUpItemServiceJPA;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/top-up-item")
public class TopUpItemController {
    @Autowired
    TopUpItemServiceJPA topUpItemServiceJPA;

    @GetMapping(value = "/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> request(@PathVariable(value = "gameId") String gameId,
                                          HttpServletRequest httpServletRequest) {
        Object result = topUpItemServiceJPA.getAll(gameId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
