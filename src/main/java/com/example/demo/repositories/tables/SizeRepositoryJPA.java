package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.ItemTypeEntity;
import com.example.demo.repositories.tables.entities.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepositoryJPA extends JpaRepository<SizeEntity, Long> {
    @Query(value = "SELECT id, name, code FROM SizeEntity")
    List<SizeEntity> getAllSize();
}
