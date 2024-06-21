package com.example.demo.services;

import com.example.demo.controllers.product.type.ProductTypeDTO;
import com.example.demo.repositories.tables.ProductCategoryEntity;
import com.example.demo.repositories.tables.ProductCategoryRepositoryJPA;
import com.example.demo.repositories.tables.ProductTypeRepositoryJPA;
import com.example.demo.repositories.tables.entities.ProductTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductCategoryServiceJPA {
    @Autowired
    ProductCategoryRepositoryJPA productCategoryRepositoryJPA;

    public Object getAll() {
        return productCategoryRepositoryJPA.getAll();
    }

    public void create(ProductCategoryDTO params) {
        ProductCategoryEntity productEntity = new ProductCategoryEntity();
        productEntity.setName(params.getName());
        productEntity.setProductId(params.getProductId());
        productCategoryRepositoryJPA.save(productEntity);
    }

    public void save(ProductCategoryEntity productCategory) {
        productCategoryRepositoryJPA.save(productCategory);
    }

    public List<ProductCategoryEntity> findByProductId(Long productId) {
        return productCategoryRepositoryJPA.findByProductId(productId);
    }
}
