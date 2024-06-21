package com.example.demo.services;

import com.example.demo.repositories.tables.ProductCategoryEntity;
import com.example.demo.repositories.tables.ProductCategoryRepositoryJPA;
import com.example.demo.repositories.tables.ProductFeeEntity;
import com.example.demo.repositories.tables.ProductFeeRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductFeeServiceJPA {
    @Autowired
    ProductFeeRepositoryJPA productFeeRepositoryJPA;

    public Object getAll() {
        return productFeeRepositoryJPA.getAll();
    }

    public void save(ProductFeeEntity productFeeEntity) {
        productFeeRepositoryJPA.save(productFeeEntity);
    }

    public List<ProductFeeEntity> findByProductId(Long productId) {
        return productFeeRepositoryJPA.findByProductId(productId);
    }
}
