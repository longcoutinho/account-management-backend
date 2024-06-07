package com.example.demo.repositories.tables;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repositories.tables.entities.CardOrderEntity;

import java.util.List;

public interface CardOrderRepositoryJPA extends JpaRepository<CardOrderEntity, String> {
    List<CardOrderEntity> findByCreateUser(String username);
}
