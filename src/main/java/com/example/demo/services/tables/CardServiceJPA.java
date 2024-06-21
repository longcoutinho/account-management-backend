package com.example.demo.services.tables;

import com.example.demo.dtos.CardDTO;
import com.example.demo.repositories.tables.CardRepositoryJPA;
import com.example.demo.repositories.tables.entities.CardEntity;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceJPA {
    @Autowired
    CardRepositoryJPA cardRepositoryJPA;

    public Object getAll() {
        return cardRepositoryJPA.getAll();
    }

    public void modify(CardDTO params, Long id) {
        Optional<CardEntity> optionalCardEntity = cardRepositoryJPA.findById(id);
        if (optionalCardEntity.isEmpty()) throw new CustomException(ErrorApp.ACCESS_DENIED);
        CardEntity cardEntity = optionalCardEntity.get();
        if (params.getName() != null) {
            cardEntity.setName(params.getName());
        }
        if (params.getImageUrl() != null) {
            cardEntity.setImage(params.getImageUrl());
        }
        cardRepositoryJPA.save(cardEntity);
    }

    public void create(CardDTO params) {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setImage(params.getImageUrl());
        cardEntity.setName(params.getName());
        cardRepositoryJPA.save(cardEntity);
    }

    public void remove(Long id) {
        Optional<CardEntity> optionalCardEntity = cardRepositoryJPA.findById(id);
        if (optionalCardEntity.isEmpty()) throw new CustomException(ErrorApp.ACCESS_DENIED);
        CardEntity cardEntity = optionalCardEntity.get();
        cardRepositoryJPA.delete(cardEntity);
    }

    public CardEntity findById(Long cardId) {
        return cardRepositoryJPA.findById(cardId).get();
    }
}
