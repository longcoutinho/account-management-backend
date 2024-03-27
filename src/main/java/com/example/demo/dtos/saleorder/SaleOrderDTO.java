package com.example.demo.dtos.saleorder;

import lombok.Data;

@Data
public class SaleOrderDTO {
    Long itemId;
    Long amount;
    String createUser;
}
