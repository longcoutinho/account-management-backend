package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFeeRepositoryJPA extends JpaRepository<ProductFeeEntity, Long> {
    @Query(value = "SELECT c FROM ProductFeeEntity c")
    List<ProductFeeEntity> getAll();

    List<ProductFeeEntity> findByProductId(Long productId);
}
