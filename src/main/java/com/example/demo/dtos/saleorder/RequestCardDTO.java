package com.example.demo.dtos.saleorder;

import com.example.demo.dtos.CardDTO;
import lombok.Data;

import java.util.List;

@Data
public class RequestCardDTO {
    List<CardDTO> listCard;
    String paymentCode;
    Long price;
}
