package com.example.demo.dtos;

import lombok.Data;

@Data
public class SaleOrderDTO {
    String userId;
    String itemId;
    Long amount;
}
