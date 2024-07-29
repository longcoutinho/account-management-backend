package com.example.demo.services.tables;

import com.example.demo.repositories.tables.CardFeeRepositoryJPA;
import com.example.demo.repositories.tables.CardItemRepositoryJPA;
import com.example.demo.repositories.tables.entities.CardFeeEntity;
import com.example.demo.repositories.tables.entities.CardItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardFeeServiceJPA {
    @Autowired
    CardFeeRepositoryJPA cardFeeRepositoryJPA;

    public CardFeeEntity findByCardItemIdAndPaymentMethodCode(Long cardId, String paymentMethodCode) {
        return cardFeeRepositoryJPA.findByCardItemIdAndPaymentMethodCode(cardId, paymentMethodCode);
    }
}
