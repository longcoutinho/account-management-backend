package com.example.demo.services;

import com.example.demo.controllers.product.type.ProductTypeDTO;
import com.example.demo.repositories.tables.ProductDetailRepositoryJPA;
import com.example.demo.repositories.tables.ProductTypeRepositoryJPA;
import com.example.demo.repositories.tables.entities.ProductDetailEntity;
import com.example.demo.repositories.tables.entities.ProductTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductDetailServiceJPA {
    @Autowired
    ProductDetailRepositoryJPA productDetailRepositoryJPA;

    public Object getAll() {
        return productDetailRepositoryJPA.getAll();
    }

    public void create(ProductDetailDTO params) {
        ProductDetailEntity productDetailEntity = new ProductDetailEntity();
        productDetailEntity.setProductId(params.getProductId());
        productDetailEntity.setDescription(params.getDescription());
        productDetailRepositoryJPA.save(productDetailEntity);
    }

    public void save(ProductDetailEntity productDetailEntity) {
        productDetailRepositoryJPA.save(productDetailEntity);
    }

    public ProductDetailEntity findByProductId(Long productId) {
        return productDetailRepositoryJPA.findByProductId(productId);
    }
}
