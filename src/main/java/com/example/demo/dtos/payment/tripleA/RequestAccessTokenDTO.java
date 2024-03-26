package com.example.demo.dtos.payment.tripleA;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestAccessTokenDTO {
    String client_id;
    String client_secret;
    String grant_type;
}
