package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepositoryJPA extends JpaRepository<CardEntity, Long> {
}
