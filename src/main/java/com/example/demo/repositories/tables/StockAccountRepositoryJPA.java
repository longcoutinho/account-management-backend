package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.ItemTypeEntity;
import com.example.demo.repositories.tables.entities.SizeEntity;
import com.example.demo.repositories.tables.entities.StockAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockAccountRepositoryJPA extends JpaRepository<StockAccountEntity, Long> {
    @Query("select e from StockAccountEntity e where :itemId is null or :itemId = e.itemId")
    List<StockAccountEntity> getAll(String itemId);

    @Query("select e from StockAccountEntity e where e.itemId = :itemId and e.status = 0")
    List<StockAccountEntity> getRandomAccount(String itemId);

    StockAccountEntity findById(String accountId);

    @Query("select count(e) from StockAccountEntity e where e.itemId = :id and e.status = 0")
    Long findByItemId(Long id);
}
