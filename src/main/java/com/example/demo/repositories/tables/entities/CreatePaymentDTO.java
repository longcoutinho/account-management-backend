package com.example.demo.repositories.tables.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreatePaymentDTO {
    String paymentCode;
    String username;
    float price;
    String orderId;
}
