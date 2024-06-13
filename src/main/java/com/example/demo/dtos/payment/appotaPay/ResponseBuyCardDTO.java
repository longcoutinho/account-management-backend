package com.example.demo.dtos.payment.appotaPay;

import com.example.demo.dtos.card.CardOrderDTO;
import com.example.demo.services.tables.item.CardInfo;
import lombok.Data;

import java.util.List;

@Data
public class ResponseBuyCardDTO {
    String errorCode;
    String message;
    List<CardInfo> cards;
}
