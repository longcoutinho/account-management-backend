package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.CardEntity;
import com.example.demo.repositories.tables.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryJPA extends JpaRepository<ProductEntity, Long> {
    @Query(value = "SELECT c FROM ProductEntity c")
    List<ProductEntity> getAll();
}
