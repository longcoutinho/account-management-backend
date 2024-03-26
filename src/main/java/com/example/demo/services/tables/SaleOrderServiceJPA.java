package com.example.demo.services.tables;

import com.example.demo.dtos.*;
import com.example.demo.kafka.KafkaProducer;
import com.example.demo.repositories.tables.SaleOrderRepositoryJPA;
import com.example.demo.repositories.tables.entities.SaleOrderEntity;
import com.example.demo.repositories.tables.entities.GameEntity;
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
    GameServiceJPA stockAccountServiceJPA;

    @Autowired
    ItemServiceJPA itemServiceJPA;

    @Autowired
    UserServiceJPA userServiceJPA;

    public Object create(SaleOrderDTO params) {
        for(int i = 0; i < params.getAmount(); i++) {
            SaleOrderEntity saleOrderEntity = new SaleOrderEntity();
            saleOrderEntity.setId(String.valueOf(UUID.randomUUID()));
            saleOrderEntity.setCreateDate(new Date(System.currentTimeMillis()));
            saleOrderEntity.setStatus(0L);
            saleOrderEntity.setAccountId(null);
            SaleOrderEntity saved = saleOrderRepositoryJPA.save(saleOrderEntity);
//            kafkaProducer.sendOrderMessage(saved.getId());
        }
        return 1L;
    }

    public SaleOrderResponseSummaryDTO getAll(SaleOrderDTO params) {
        List<SaleOrderResponseDTO> res = new LinkedList<>();
        List<SaleOrderEntity> list = saleOrderRepositoryJPA.getAll();
        Long amount = 0L;
        for(SaleOrderEntity saleOrderEntity: list) {
            SaleOrderResponseDTO saleOrderResponseDTO = new SaleOrderResponseDTO();
            saleOrderResponseDTO.setId(saleOrderEntity.getId());
            saleOrderResponseDTO.setCreateDate(saleOrderEntity.getCreateDate());
            saleOrderResponseDTO.setStatus(saleOrderEntity.getStatus());
            saleOrderResponseDTO.setUserId(saleOrderEntity.getUsername());
            res.add(saleOrderResponseDTO);
        }
        SaleOrderResponseSummaryDTO result = new SaleOrderResponseSummaryDTO();
        result.setListSaleOrder(res);
        result.setTotalRequest((long) res.size());
        result.setTotalAmount(amount);
        return result;
    }

    public void processOrder(String orderId) {
        SaleOrderEntity saleOrderEntity = saleOrderRepositoryJPA.findById(orderId);
        TopUpRequestDTO topUpRequestDTO = new TopUpRequestDTO();
        topUpRequestDTO.setUsername(saleOrderEntity.getUsername());
        userServiceJPA.addBalance(topUpRequestDTO);
    }
}
