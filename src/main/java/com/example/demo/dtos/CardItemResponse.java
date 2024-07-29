package com.example.demo.dtos;

import com.example.demo.repositories.tables.entities.CardFeeEntity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CardItemResponse {
    Long id;

    Long cardId;

    String name;

    List<CardFeeEntity> listFees;
}
