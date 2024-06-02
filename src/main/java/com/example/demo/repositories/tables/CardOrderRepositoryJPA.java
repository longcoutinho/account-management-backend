package com.example.demo.repositories.tables;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repositories.tables.entities.CardOrderEntity;

public interface CardOrderRepositoryJPA extends JpaRepository<CardOrderEntity, String> {

}
