package com.example.demo.repositories.tables;
import com.example.demo.dtos.TopUpRequestDTO;
import com.example.demo.repositories.tables.entities.ItemEntity;
import com.example.demo.repositories.tables.entities.ItemTypeEntity;
import com.example.demo.repositories.tables.entities.TopUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopUpRepositoryJPA extends JpaRepository<TopUpEntity, Long> {

    TopUpEntity findById(String id);

    @Query("select e from TopUpEntity e")
    List<TopUpEntity> getAll();
}
