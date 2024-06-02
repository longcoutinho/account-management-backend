package com.example.demo.dtos.payment.tripleA;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponsePaymentDTO {
    String hosted_url;
    String message;
}
