package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.CardFeeEntity;
import com.example.demo.repositories.tables.entities.CardItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardFeeRepositoryJPA extends JpaRepository<CardFeeEntity, Long> {
    CardFeeEntity findByCardItemIdAndPaymentMethodCode(Long cardId, String paymentMethodCode);

    List<CardFeeEntity> findByCardItemId(Long id);
}
