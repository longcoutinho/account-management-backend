package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.SaleOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleOrderRepositoryJPA extends JpaRepository<SaleOrderEntity, Long> {

    @Query("select e from SaleOrderEntity e order by e.createDate desc")
    List<SaleOrderEntity> getAll();

    SaleOrderEntity findById(String orderId);
}
