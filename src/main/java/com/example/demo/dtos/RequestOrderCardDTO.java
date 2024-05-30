package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestOrderCardDTO {
    List<CardDTO> cardInfo;
    String paymentMethod;
}
