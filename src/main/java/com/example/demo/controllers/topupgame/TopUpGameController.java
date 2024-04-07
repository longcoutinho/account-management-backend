package com.example.demo.controllers.topupgame;

import com.example.demo.dtos.TopUpRequestDTO;
import com.example.demo.dtos.topup.RequestTokenLordMobile;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.services.topupgame.TopUpGameServiceJPA;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/top-up-game")
public class TopUpGameController {
    @Autowired
    TopUpGameServiceJPA topUpGameServiceJPA;

    @PostMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> request(@Valid @RequestBody TopUpRequestDTO params,
                                          HttpServletRequest httpServletRequest) {
        UserEntity userEntity = (UserEntity) httpServletRequest.getAttribute("userInfo");
        params.setUsername(userEntity.getUsername());
        Object result = topUpGameServiceJPA.createRequest(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/lord-mobile/send-otp/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> request(@PathVariable(value = "id") String id,
                                          HttpServletRequest httpServletRequest) {
        Object result = topUpGameServiceJPA.sendOtpLordMobile(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/lord-mobile/token/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> request(@PathVariable(value = "id") String id,
                                          @RequestBody RequestTokenLordMobile params) {
        Object result = topUpGameServiceJPA.getTokenLordMobile(id, params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
