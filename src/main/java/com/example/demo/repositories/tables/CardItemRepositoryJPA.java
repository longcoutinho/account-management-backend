package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.CardItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardItemRepositoryJPA extends JpaRepository<CardItemEntity, Long> {
    @Query("select e from CardItemEntity e join CardFeeEntity c on e.id = c.cardItemId where e.cardId = :id order by c.price")
    List<CardItemEntity> findByCardId(Long id);

    @Query("select e from CardItemEntity e join CardFeeEntity c on e.id = c.cardItemId order by c.price")
    List<CardItemEntity> getAll();
}
