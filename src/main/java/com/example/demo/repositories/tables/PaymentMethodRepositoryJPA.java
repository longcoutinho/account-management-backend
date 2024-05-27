package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodRepositoryJPA extends JpaRepository<PaymentMethodEntity, Long> {
    @Query(value = "SELECT e FROM PaymentMethodEntity e where e.isActive = 1")
    List<PaymentMethodEntity> getAll();
}
