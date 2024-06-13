package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.CardEntity;
import com.example.demo.repositories.tables.entities.CardOrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardOrderDetailRepositoryJPA extends JpaRepository<CardOrderDetailEntity, Long> {
}
