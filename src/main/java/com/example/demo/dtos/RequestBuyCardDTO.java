package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestBuyCardDTO {
    String partnerRefId;
    String productCode;
    String quantity;
    String signature;
}
