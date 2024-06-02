package com.example.demo.services.tables.item;

import com.example.demo.repositories.tables.CardItemRepositoryJPA;
import com.example.demo.repositories.tables.entities.CardItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardItemServiceJPA {
    @Autowired
    CardItemRepositoryJPA cardItemRepositoryJPA;

    public Object getItemByCardId(Long id) {
        return cardItemRepositoryJPA.findByCardId(id);
    }

    public CardItemEntity findById(Long itemId) {
        return cardItemRepositoryJPA.findById(itemId).get();
    }

    public Object getAll() {
        return cardItemRepositoryJPA.getAll();
    }
}
