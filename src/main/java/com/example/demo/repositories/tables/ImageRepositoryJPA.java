package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.ItemImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepositoryJPA extends JpaRepository<ItemImageEntity, Long> {

    List<ItemImageEntity> findByItemId(Long itemId);
}
