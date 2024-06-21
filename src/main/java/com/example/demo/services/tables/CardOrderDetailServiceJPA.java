package com.example.demo.services.tables;

import com.example.demo.repositories.tables.CardOrderDetailRepositoryJPA;
import com.example.demo.repositories.tables.entities.CardOrderDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardOrderDetailServiceJPA {
    @Autowired
    CardOrderDetailRepositoryJPA cardOrderDetailRepositoryJPA;

    public CardOrderDetailEntity save(CardOrderDetailEntity cardOrderDetailEntity) {
        return cardOrderDetailRepositoryJPA.save(cardOrderDetailEntity);
    }

    public List<CardOrderDetailEntity> findByCardOrderId(String id) {
        return cardOrderDetailRepositoryJPA.findByCardOrderId(id);
    }
}
