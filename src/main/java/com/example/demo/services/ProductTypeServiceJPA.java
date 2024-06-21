package com.example.demo.services;

import com.example.demo.controllers.product.ProductDTO;
import com.example.demo.controllers.product.type.ProductTypeDTO;
import com.example.demo.repositories.tables.ProductRepositoryJPA;
import com.example.demo.repositories.tables.ProductTypeRepositoryJPA;
import com.example.demo.repositories.tables.entities.ProductEntity;
import com.example.demo.repositories.tables.entities.ProductTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductTypeServiceJPA {
    @Autowired
    ProductTypeRepositoryJPA productTypeRepositoryJPA;

    public Object getAll() {
        return productTypeRepositoryJPA.getAll();
    }

    public void create(ProductTypeDTO params) {
        ProductTypeEntity productEntity = new ProductTypeEntity();
        productEntity.setName(params.getName());
        productEntity.setCreate_date(new Date(System.currentTimeMillis()));
        productEntity.setCreate_user(params.getUsername());
        productTypeRepositoryJPA.save(productEntity);
    }

    public Object getDetail(Long productId) {
        return null;
    }
}
