package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.CardOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleOrderRepositoryJPA extends JpaRepository<CardOrderEntity, Long> {

    @Query("select e from CardOrderEntity e order by e.createDate desc")
    List<CardOrderEntity> getAll();

    CardOrderEntity findById(String orderId);
}
