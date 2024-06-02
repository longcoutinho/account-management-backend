package com.example.demo.dtos.topup;

import lombok.Data;

@Data
public class PaymentStatusRequestDTO {
    Long orderCode;
    String id;
}
