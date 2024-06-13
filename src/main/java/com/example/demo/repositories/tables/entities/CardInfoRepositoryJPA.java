package com.example.demo.repositories.tables.entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardInfoRepositoryJPA extends JpaRepository<CardInfoEntity, Long> {
}
