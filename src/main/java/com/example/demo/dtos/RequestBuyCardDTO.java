package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RequestBuyCardDTO {
    String partnerRefId;
    String productCode;
    String quantity;
    String signature;

    public RequestBuyCardDTO(String partnerRefId, String productCode, String quantity) {
        this.partnerRefId = partnerRefId;
        this.productCode = productCode;
        this.quantity = quantity;
    }
}
