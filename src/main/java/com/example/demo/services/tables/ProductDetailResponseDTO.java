package com.example.demo.services.tables;

import com.example.demo.repositories.tables.ProductCategoryEntity;
import com.example.demo.repositories.tables.ProductFeeEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailResponseDTO {
    String description;
    List<String> imagePathList;
    List<ProductCategoryEntity> categoryList;
    List<ProductFeeEntity> feeList;
    Long typeId;
    String name;
}
