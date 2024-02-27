package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.ItemTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemTypeRepositoryJPA extends JpaRepository<ItemTypeEntity, Long> {
    List<ItemTypeEntity> findByCode(String code);

    List<ItemTypeEntity> findByLevelAndParentIdOrderByName(Long level, Long parentId);

    void deleteByParentId(Long parentId);
}
