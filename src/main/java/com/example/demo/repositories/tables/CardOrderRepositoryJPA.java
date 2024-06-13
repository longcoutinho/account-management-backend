package com.example.demo.repositories.tables;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repositories.tables.entities.CardOrderEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardOrderRepositoryJPA extends JpaRepository<CardOrderEntity, String> {
    @Query("select e from CardOrderEntity e where e.createUser = :username order by e.createDate desc")
    List<CardOrderEntity> findByCreateUser(String username);
}
