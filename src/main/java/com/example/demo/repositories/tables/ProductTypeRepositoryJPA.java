package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.CardEntity;
import com.example.demo.repositories.tables.entities.ProductEntity;
import com.example.demo.repositories.tables.entities.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepositoryJPA extends JpaRepository<ProductTypeEntity, Long> {
    @Query(value = "SELECT c FROM ProductTypeEntity c")
    List<ProductTypeEntity> getAll();
}
