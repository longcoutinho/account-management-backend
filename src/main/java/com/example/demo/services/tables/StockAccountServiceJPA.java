package com.example.demo.services.tables;

import com.example.demo.dtos.StockAccountDTO;
import com.example.demo.repositories.tables.StockAccountRepositoryJPA;
import com.example.demo.repositories.tables.entities.StockAccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class StockAccountServiceJPA {
    @Autowired
    StockAccountRepositoryJPA stockAccountRepositoryJPA;

    public Object createNewAccount(StockAccountDTO params) {
        StockAccountEntity stockAccountEntity = new StockAccountEntity();
        stockAccountEntity.setId(String.valueOf(UUID.randomUUID()));
        stockAccountEntity.setCreateDate(new Date(System.currentTimeMillis()));
        stockAccountEntity.setCreateUser(null);
        stockAccountEntity.setStatus(0L);
        stockAccountEntity.setUsername(params.getUsername());
        stockAccountEntity.setItemId(params.getItemId());
        stockAccountEntity.setPassword(params.getPassword());
        stockAccountRepositoryJPA.save(stockAccountEntity);
        return stockAccountEntity;
    }

    public Object getAll(StockAccountDTO params) {
        return stockAccountRepositoryJPA.getAll(params.getItemId());
    }

    public StockAccountEntity orderAccount(String itemId) {
        List<StockAccountEntity> stockAccountEntity = stockAccountRepositoryJPA.getRandomAccount(itemId);
        if (stockAccountEntity.size() == 0) return null;
        Random rand = new Random();
        StockAccountEntity randomElement = stockAccountEntity.get(rand.nextInt(stockAccountEntity.size()));
        randomElement.setStatus(1L);
        stockAccountRepositoryJPA.save(randomElement);
        return randomElement;
    }

    public StockAccountEntity findById(String accountId) {
        return stockAccountRepositoryJPA.findById(accountId);
    }
}
