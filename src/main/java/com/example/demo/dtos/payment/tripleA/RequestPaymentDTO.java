package com.example.demo.dtos.payment.tripleA;

import lombok.Data;

@Data
public class RequestPaymentDTO {
    String type;
    String merchant_key;
    String order_currency;
    Long order_amount;
    String payer_id;
    String order_id;
    String cancel_url;
    String success_url;
}
