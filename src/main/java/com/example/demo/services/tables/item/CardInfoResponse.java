package com.example.demo.services.tables.item;

import lombok.Data;

import java.util.List;

@Data
public class CardInfoResponse {
    List<CardInfo> cards;
    String cardItemId;
}
