package com.example.demo.repositories.tables;
import com.example.demo.dtos.SizeDTO;
import com.example.demo.repositories.tables.entities.ColorEntity;
import com.example.demo.repositories.tables.entities.ItemTypeEntity;
import com.example.demo.repositories.tables.entities.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepositoryJPA extends JpaRepository<ColorEntity, Long> {
    @Query(value = "SELECT id, code, name FROM ColorEntity")
    Object getAllColor();
}
