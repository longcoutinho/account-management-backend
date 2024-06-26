package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.example.demo.dtos.card.CardOrderDTO;
import com.example.demo.repositories.tables.entities.UserEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestOrderCardDTO {
    List<CardOrderDTO> cardInfo;
    String paymentMethodCode;
    UserEntity userInfo;
    Long totalPrice;
    String ip_address;
}
