package com.example.demo.controllers;

import com.example.demo.dtos.TopUpRequestDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.dtos.user.AdjustBalanceDTO;
import com.example.demo.dtos.user.RequestUserDTO;
import com.example.demo.dtos.user.ResetPasswordDTO;
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
        params.setType(1L);
        Object result = userServiceJPA.createNewUser(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/admin-account/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerAdmin(@Valid @RequestBody UserDTO params) throws Exception {
        params.setType(2L);
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
        params.setType(1L);
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
        params.setType(2L);
        Object result = userServiceJPA.loginUser(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/add-balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addBalance(@RequestBody TopUpRequestDTO params) {
        Object result = userServiceJPA.addBalance(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserById(@PathVariable(value="id", required = true) String id) {
        Object result = userServiceJPA.getUserById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll(RequestUserDTO params) {
        Object result = userServiceJPA.getAll(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll(@PathVariable(value = "username") String username) {
        Object result = userServiceJPA.findByUsername(username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/reset-password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> resetPassword(@Valid @RequestBody ResetPasswordDTO params) {
        Object result = userServiceJPA.resetPassword(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/adjust-balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> adjustBalance(@Valid @RequestBody AdjustBalanceDTO params) {
        Object result = userServiceJPA.adjustBalance(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
