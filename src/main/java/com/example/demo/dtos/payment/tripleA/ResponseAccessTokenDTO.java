package com.example.demo.dtos.payment.tripleA;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseAccessTokenDTO {
    String access_token;
    String token_type;
    Long expires_in;
}
