package com.example.demo.repositories.tables;

import com.example.demo.repositories.tables.entities.PaymentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepositoryJPA extends JpaRepository<PaymentEntity, Long>{
}
