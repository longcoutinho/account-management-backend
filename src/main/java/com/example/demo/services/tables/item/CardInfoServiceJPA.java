package com.example.demo.services.tables.item;

import com.example.demo.repositories.tables.CardInfoRepositoryJPA;
import com.example.demo.repositories.tables.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardInfoServiceJPA {
    @Autowired
    CardInfoRepositoryJPA cardInfoRepositoryJPA;

    public void save(CardInfoEntity cardInfoEntity) {
        cardInfoRepositoryJPA.save(cardInfoEntity);
    }

    public List<CardInfoEntity> findByOrderDetailId(String id) {
        return cardInfoRepositoryJPA.findByOrderDetailId(id);
    }
}
