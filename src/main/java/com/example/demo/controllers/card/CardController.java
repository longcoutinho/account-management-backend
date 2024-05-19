package com.example.demo.controllers.card;

import com.example.demo.dtos.CardDTO;
import com.example.demo.services.tables.CardServiceJPA;
import com.example.demo.utils.constants.FnCommon;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card")
public class CardController {
    @Autowired
    CardServiceJPA cardServiceJPA;

    /**
     * Lay thong tin cac loai card
     * @return
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> get() {
        return new ResponseEntity<>(cardServiceJPA.getAll(), HttpStatus.OK);
    }

    /**
     * Them moi loai card
     * @param params
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody CardDTO params,
                                         HttpServletRequest httpServletRequest) {
        if (!FnCommon.isAdmin(httpServletRequest)) throw new CustomException(ErrorApp.ACCESS_DENIED);
        cardServiceJPA.create(params);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Chinh sua thong tin loai card
     * @param params
     */
    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> modify(@RequestBody CardDTO params,
                                         @PathVariable(value = "id") Long id,
                                         HttpServletRequest httpServletRequest) {
        if (!FnCommon.isAdmin(httpServletRequest)) throw new CustomException(ErrorApp.ACCESS_DENIED);
        cardServiceJPA.modify(params, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Xoa loai card
     */
    @PostMapping(value = "/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> remove(@PathVariable(value = "id") Long id,
                                         HttpServletRequest httpServletRequest) {
        if (!FnCommon.isAdmin(httpServletRequest)) throw new CustomException(ErrorApp.ACCESS_DENIED);
        cardServiceJPA.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
