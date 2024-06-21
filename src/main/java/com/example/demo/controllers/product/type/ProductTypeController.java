package com.example.demo.controllers.product.type;

import com.example.demo.controllers.product.ProductDTO;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.services.ProductTypeServiceJPA;
import com.example.demo.services.tables.ProductServiceJPA;
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
@RequestMapping(value = "/product-type")
public class ProductTypeController {
    @Autowired
    ProductTypeServiceJPA productTypeServiceJPA;

    /**
     * Lay thong tin cac san pham
     * @return
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> get() {
        return new ResponseEntity<>(productTypeServiceJPA.getAll(), HttpStatus.OK);
    }

    /**
     * Them moi san pham
     * @param params
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody ProductTypeDTO params,
                                         HttpServletRequest httpServletRequest) {
        if (!FnCommon.isAdmin(httpServletRequest)) throw new CustomException(ErrorApp.ACCESS_DENIED);
        UserEntity userEntity = (UserEntity) httpServletRequest.getAttribute("userInfo");
        params.setUsername(userEntity.getUsername());
        productTypeServiceJPA.create(params);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    /**
//     * Chinh sua thong tin loai card
//     * @param params
//     */
//    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> modify(@RequestBody CardDTO params,
//                                         @PathVariable(value = "id") Long id,
//                                         HttpServletRequest httpServletRequest) {
//        if (!FnCommon.isAdmin(httpServletRequest)) throw new CustomException(ErrorApp.ACCESS_DENIED);
//        cardServiceJPA.modify(params, id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    /**
//     * Xoa loai card
//     */
//    @PostMapping(value = "/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> remove(@PathVariable(value = "id") Long id,
//                                         HttpServletRequest httpServletRequest) {
//        if (!FnCommon.isAdmin(httpServletRequest)) throw new CustomException(ErrorApp.ACCESS_DENIED);
//        cardServiceJPA.remove(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
