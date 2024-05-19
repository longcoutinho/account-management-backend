package com.example.demo.controllers.user;

import com.example.demo.dtos.UserDTO;
import com.example.demo.dtos.user.AdjustBalanceDTO;
import com.example.demo.dtos.user.RequestUserDTO;
import com.example.demo.dtos.user.ResetPasswordDTO;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.services.topupgame.TopUpItemServiceJPA;
import com.example.demo.services.tables.UserServiceJPA;
import com.example.demo.utils.constants.FnCommon;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
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
    TopUpItemServiceJPA userAdminServiceJPA;

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

//    @PostMapping(value = "/admin-account/create", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> registerAdmin(@Valid @RequestBody UserDTO params) throws Exception {
//        Object result = userServiceJPA.createNewUser(params);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    /**
     * API dang nhap user thuong
     *
     * @return
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@Valid @RequestBody UserDTO params) {
        params.setRole(UserEntity.Role.USER.value);
        Object result = userServiceJPA.loginUser(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * API dang nhap admin
     * @param params - thong tin dang nhap
     * @return
     */
    @PostMapping(value = "/login-admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> loginAdmin(@Valid @RequestBody UserDTO params) {
        params.setRole(UserEntity.Role.ADMIN.value);
        Object result = userServiceJPA.loginUser(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Lay thong tin user
     * @param params
     * @return
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll(RequestUserDTO params, HttpServletRequest httpServletRequest) {
        if (!FnCommon.isAdmin(httpServletRequest)) throw new CustomException(ErrorApp.ACCESS_DENIED);
        Object result = userServiceJPA.getAll(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Doi mat khau
     * @param params
     * @return
     */
    @PostMapping(value = "/reset-password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> resetPassword(@Valid @RequestBody ResetPasswordDTO params) {
        Object result = userServiceJPA.resetPassword(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Dieu chinh so du
     * @param params
     * @return
     */
    @PostMapping(value = "/adjust-balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> adjustBalance(@Valid @RequestBody AdjustBalanceDTO params, HttpServletRequest httpServletRequest) {
        if (!FnCommon.isAdmin(httpServletRequest)) throw new CustomException(ErrorApp.ACCESS_DENIED);
        Object result = userServiceJPA.adjustBalance(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
