package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.CardItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardItemRepositoryJPA extends JpaRepository<CardItemEntity, Long> {
    @Query("select e from CardItemEntity e where e.cardId = :id order by e.price")
    List<CardItemEntity> findByCardId(Long id);

    @Query("select e from CardItemEntity e order by e.price")
    List<CardItemEntity> getAll();
}
