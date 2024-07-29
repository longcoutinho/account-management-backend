package com.example.demo.services.tables.item;

import com.example.demo.dtos.CardItemResponse;
import com.example.demo.repositories.tables.CardItemRepositoryJPA;
import com.example.demo.repositories.tables.entities.CardFeeEntity;
import com.example.demo.repositories.tables.entities.CardItemEntity;
import com.example.demo.services.tables.CardFeeServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardItemServiceJPA {
    @Autowired
    CardItemRepositoryJPA cardItemRepositoryJPA;

    @Autowired
    CardFeeServiceJPA cardFeeServiceJPA;

    public Object getItemByCardId(Long id) {
        return cardItemRepositoryJPA.findByCardId(id);
    }

    public CardItemEntity findById(Long itemId) {
        return cardItemRepositoryJPA.findById(itemId).get();
    }

    public Object getAll() {
        return cardItemRepositoryJPA.getAll();
    }

    public CardItemResponse getItemById(Long id) {
        CardItemEntity cardItemEntity = cardItemRepositoryJPA.findById(id).get();
        List<CardFeeEntity> listFees = cardFeeServiceJPA.findByCardItemId(id);
        return CardItemResponse
                .builder()
                .cardId(cardItemEntity.getCardId())
                .id(cardItemEntity.getId())
                .name(cardItemEntity.getName())
                .listFees(listFees)
                .build();
    }
}
