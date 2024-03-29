package com.example.demo.dtos.payment.appotaPay;

import lombok.Data;

import java.util.List;

@Data
public class CardDTO {
    String code;
    String serial;
    String vendor;
    Long values;
    String expiry;
    Long amount;
    String time;
    Transaction transaction;
    List<CardDTO> cards;
}
