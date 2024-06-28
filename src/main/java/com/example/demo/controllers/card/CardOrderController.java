package com.example.demo.controllers.card;

import com.example.demo.dtos.RequestOrderCardDTO;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.services.tables.CardOrderServiceJPA;
import com.example.demo.utils.constants.FnCommon;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card/order")
public class CardOrderController {
    @Autowired
    CardOrderServiceJPA cardOrderServiceJPA;

    /**
     * Mua the
     * @param request - Thong tin mua the
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody RequestOrderCardDTO request, HttpServletRequest servletRequest) {
        UserEntity userEntity = (UserEntity) servletRequest.getAttribute("userInfo");
        request.setUserInfo(userEntity);
        request.setIp_address(FnCommon.getClientIpAddress(servletRequest));
        return new ResponseEntity<>(cardOrderServiceJPA.create(request), HttpStatus.OK);
    }

    /**
     * Mua the
     * @param - Thong tin mua the
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll(HttpServletRequest servletRequest, RequestAllOrder request) {
        UserEntity userEntity = (UserEntity) servletRequest.getAttribute("userInfo");
        if (!FnCommon.isAdmin(servletRequest)) request.setUsername(userEntity.getUsername());
        return new ResponseEntity<>(cardOrderServiceJPA.getAll(request), HttpStatus.OK);
    }

    /**
     * Mua the
     * @param - Thong tin mua the
     */
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDetail(RequestCardInfoDTO request, HttpServletRequest servletRequest) {
        UserEntity userEntity = (UserEntity) servletRequest.getAttribute("userInfo");
        return new ResponseEntity<>(cardOrderServiceJPA.getDetail(request.getOrderId(), userEntity), HttpStatus.OK);
    }

    /**
     * Mua the
     * @param request - Thong tin mua the
     */
    @PostMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getInfo(@RequestBody RequestCardInfoDTO request, HttpServletRequest servletRequest) throws Exception {
        UserEntity userEntity = (UserEntity) servletRequest.getAttribute("userInfo");
        return new ResponseEntity<>(cardOrderServiceJPA.getInfo(request, userEntity.getUsername()), HttpStatus.OK);
    }
}
