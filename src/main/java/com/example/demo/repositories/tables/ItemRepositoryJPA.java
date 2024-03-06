package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.ItemEntity;
import com.example.demo.repositories.tables.entities.ItemTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepositoryJPA extends JpaRepository<ItemEntity, Long> {
    @Query("SELECT i " +
            "FROM ItemEntity i " +
            "where (:name is null or i.name like CONCAT('%',:name,'%'))" +
            "and (:typeId is null or i.typeId = :typeId)")
    List<ItemEntity> getAllItem(String name, Long typeId);
}
