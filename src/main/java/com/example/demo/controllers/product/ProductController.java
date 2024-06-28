package com.example.demo.controllers.product;

import com.example.demo.dtos.CardDTO;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.services.tables.CardServiceJPA;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductServiceJPA productServiceJPA;

    /**
     * Lay thong tin cac san pham
     * @return
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> get() {
        return new ResponseEntity<>(productServiceJPA.getAll(), HttpStatus.OK);
    }

    /**
     * Them moi san pham
     * @param params
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody ProductDTO params,
                                         HttpServletRequest httpServletRequest) throws IOException {
        if (!FnCommon.isAdmin(httpServletRequest)) throw new CustomException(ErrorApp.ACCESS_DENIED);
        UserEntity userEntity = (UserEntity) httpServletRequest.getAttribute("userInfo");
        params.setUsername(userEntity.getUsername());
        return new ResponseEntity<>(productServiceJPA.create(params), HttpStatus.OK);
    }

    /**
     * Them moi anh
     * @param
     */
    @PostMapping(value = "images/{productId}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createImage(@RequestParam("imagesList") List<MultipartFile> imagesList,
                                              @PathVariable(value = "productId") Long productId,
                                         HttpServletRequest httpServletRequest) throws IOException {
        if (!FnCommon.isAdmin(httpServletRequest)) throw new CustomException(ErrorApp.ACCESS_DENIED);
        UserEntity userEntity = (UserEntity) httpServletRequest.getAttribute("userInfo");
        productServiceJPA.saveImage(imagesList, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Lay thong tin chi tiet san pham
     * @return
     */
    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDetail(@PathVariable(value = "productId") Long productId) {
        return new ResponseEntity<>(productServiceJPA.getDetail(productId), HttpStatus.OK);
    }

    /**
     * Lay thong tin chi tiet san pham
     * @return
     */
    @PostMapping(value = "remove/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> remove(@PathVariable(value = "productId") Long productId,
                                         HttpServletRequest httpServletRequest) {
        if (!FnCommon.isAdmin(httpServletRequest)) throw new CustomException(ErrorApp.ACCESS_DENIED);
        productServiceJPA.removeById(productId);
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
