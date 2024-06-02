package com.example.demo.repositories.tables;

import com.example.demo.repositories.tables.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepositoryJPA extends JpaRepository<FileEntity, Long> {
}
