package com.example.demo.dtos;

import lombok.Data;

@Data
public class RequestBuyCardDTO {
    String transactionId;
    String productCode;
    String quantity;
    String signature;

    public RequestBuyCardDTO(String transactionId, String productCode, String quantity) {
        this.transactionId = transactionId;
        this.productCode = productCode;
        this.quantity = quantity;
    }
}
