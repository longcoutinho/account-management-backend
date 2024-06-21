package com.example.demo.controllers.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductDTO {
    String name;
    Long typeId;
    String description;
    List<String> category;
    List<PaymentDTO> price;
    String username;
}
