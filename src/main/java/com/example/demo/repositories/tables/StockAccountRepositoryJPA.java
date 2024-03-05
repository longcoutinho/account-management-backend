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
}
