package com.example.demo.controllers;

import com.example.demo.dtos.UserDTO;
import com.example.demo.services.tables.UserAdminServiceJPA;
import com.example.demo.services.tables.UserServiceJPA;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserServiceJPA userServiceJPA;

    @Autowired
    UserAdminServiceJPA userAdminServiceJPA;

    /**
     * API dang ky user moi
     *
     * @return
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@Valid @RequestBody UserDTO params) throws Exception {
        Object result = userServiceJPA.createNewUser(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * API dang ky user moi
     *
     * @return
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@Valid @RequestBody UserDTO params) {
        Object result = userServiceJPA.loginUser(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * API dang ky user moi
     *
     * @return
     */
    @PostMapping(value = "/login-admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> loginAdmin(@Valid @RequestBody UserDTO params) {
        Object result = userAdminServiceJPA.loginUser(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
