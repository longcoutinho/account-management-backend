package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepositoryJPA extends JpaRepository<GameEntity, Long> {
    @Query(value = "select e from GameEntity e")
    List<GameEntity> getAll();
}
