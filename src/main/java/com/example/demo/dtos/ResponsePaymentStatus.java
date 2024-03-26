package com.example.demo.dtos;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponsePaymentStatus {
    String id;
    Long orderCode;
    Long amount;
    Long amountPaid;
    Long amountRemaining;
    String status;
}
