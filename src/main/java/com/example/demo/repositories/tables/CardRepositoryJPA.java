package com.example.demo.repositories.tables;
import com.example.demo.dtos.CardDTO;
import com.example.demo.repositories.tables.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepositoryJPA extends JpaRepository<CardEntity, Long> {
    @Query(value = "SELECT c FROM CardEntity c")
    List<CardEntity> getAll();
}
