package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.ProductDetailEntity;
import com.example.demo.repositories.tables.entities.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepositoryJPA extends JpaRepository<ProductDetailEntity, Long> {
    @Query(value = "SELECT c FROM ProductDetailEntity c")
    List<ProductDetailEntity> getAll();

    ProductDetailEntity findByProductId(Long productId);
}
