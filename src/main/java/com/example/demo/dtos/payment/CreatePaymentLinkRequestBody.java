package com.example.demo.dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentLinkRequestBody {
    private String productName;
    private String description;
    private String returnUrl;
    private String cancelUrl;
    private Long price;
    private Long orderCode;
    private String username;
}