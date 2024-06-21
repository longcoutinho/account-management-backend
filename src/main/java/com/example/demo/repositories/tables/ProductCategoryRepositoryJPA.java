package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepositoryJPA extends JpaRepository<ProductCategoryEntity, Long> {
    @Query(value = "SELECT c FROM ProductCategoryEntity c")
    List<CardEntity> getAll();

    List<ProductCategoryEntity> findByProductId(Long productId);
}
