package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.TopUpItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopUpItemRepositoryJPA extends JpaRepository<TopUpItemEntity, Long> {
    @Query("select e from TopUpItemEntity e")
    List<TopUpItemEntity> getAll(String gameId);
}
