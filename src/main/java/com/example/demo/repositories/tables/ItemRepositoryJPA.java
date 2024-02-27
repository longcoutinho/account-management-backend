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
            "where (:lv1Id is null or i.lv1TypeId = :lv1Id) " +
            "and (:lv2Id is null or i.lv2TypeId = :lv2Id) " +
            "and (:name is null or i.name = :name) ")
    List<ItemEntity> getAllItem(Long lv1Id, Long lv2Id, String name);

    List<ItemEntity> findByLv1TypeId(Long lv1TypeId);

    List<ItemEntity> findByLv2TypeId(Long lv2TypeId);
}
