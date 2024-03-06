package com.example.demo.services.tables;

import com.example.demo.dtos.*;
import com.example.demo.kafka.KafkaProducer;
import com.example.demo.repositories.tables.SaleOrderRepositoryJPA;
import com.example.demo.repositories.tables.entities.ItemEntity;
import com.example.demo.repositories.tables.entities.SaleOrderEntity;
import com.example.demo.repositories.tables.entities.StockAccountEntity;
import com.example.demo.services.tables.item.ItemServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class SaleOrderServiceJPA {
    @Autowired
    SaleOrderRepositoryJPA saleOrderRepositoryJPA;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    StockAccountServiceJPA stockAccountServiceJPA;

    @Autowired
    ItemServiceJPA itemServiceJPA;

    @Autowired
    UserServiceJPA userServiceJPA;

    public Object create(SaleOrderDTO params) {
        for(int i = 0; i < params.getAmount(); i++) {
            SaleOrderEntity saleOrderEntity = new SaleOrderEntity();
            saleOrderEntity.setId(String.valueOf(UUID.randomUUID()));
            saleOrderEntity.setUserId(params.getUserId());
            saleOrderEntity.setCreateDate(new Date(System.currentTimeMillis()));
            saleOrderEntity.setItemId(params.getItemId());
            saleOrderEntity.setStatus(0L);
            saleOrderEntity.setAccountId(null);
            SaleOrderEntity saved = saleOrderRepositoryJPA.save(saleOrderEntity);
            kafkaProducer.sendOrderMessage(saved.getId());
        }
        return 1L;
    }

    public List<SaleOrderResponseDTO> getAll(SaleOrderDTO params) {
        List<SaleOrderResponseDTO> res = new LinkedList<>();
        List<SaleOrderEntity> list = saleOrderRepositoryJPA.getAll();
        for(SaleOrderEntity saleOrderEntity: list) {
            SaleOrderResponseDTO saleOrderResponseDTO = new SaleOrderResponseDTO();
            saleOrderResponseDTO.setId(saleOrderEntity.getId());
            saleOrderResponseDTO.setCreateDate(saleOrderEntity.getCreateDate());
            saleOrderResponseDTO.setStatus(saleOrderEntity.getStatus());
            StockAccountEntity stockAccountEntity = stockAccountServiceJPA.findById(saleOrderEntity.getAccountId());
            if (stockAccountEntity != null) {
                saleOrderResponseDTO.setUsername(stockAccountEntity.getUsername());
                saleOrderResponseDTO.setPassword(stockAccountEntity.getPassword());
            }
            res.add(saleOrderResponseDTO);
        }
        return res;
    }

    public void processOrder(String orderId) {
        SaleOrderEntity saleOrderEntity = saleOrderRepositoryJPA.findById(orderId);
        ItemDTO itemEntity = itemServiceJPA.getItemById(Long.valueOf(saleOrderEntity.getItemId()));
        TopUpRequestDTO topUpRequestDTO = new TopUpRequestDTO();
        topUpRequestDTO.setUsername(saleOrderEntity.getUserId());
        topUpRequestDTO.setAmount(-itemEntity.getPrice());
        userServiceJPA.addBalance(topUpRequestDTO);
        StockAccountEntity stockAccountEntity = stockAccountServiceJPA.orderAccount(saleOrderEntity.getItemId());
        if (stockAccountEntity != null) {
            saleOrderEntity.setStatus(1L);
            saleOrderEntity.setAccountId(stockAccountEntity.getId());
            saleOrderRepositoryJPA.save(saleOrderEntity);
        }
        else {
            topUpRequestDTO.setAmount(itemEntity.getPrice());
            userServiceJPA.addBalance(topUpRequestDTO);
            saleOrderEntity.setStatus(2L);
            saleOrderRepositoryJPA.save(saleOrderEntity);
        }
    }
}
