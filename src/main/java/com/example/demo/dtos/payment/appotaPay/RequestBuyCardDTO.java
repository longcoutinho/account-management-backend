package com.example.demo.dtos.payment.appotaPay;

import lombok.Data;

@Data
public class RequestBuyCardDTO {
    String partnerRefId;
    String productCode;
    Long quantity;
    String signature;
}
