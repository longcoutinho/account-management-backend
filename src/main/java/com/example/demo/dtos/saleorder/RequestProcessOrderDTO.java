package com.example.demo.dtos.saleorder;

import lombok.Data;

@Data
public class RequestProcessOrderDTO {
    String orderId;
    String paymentReference;
    String createUser;
}
