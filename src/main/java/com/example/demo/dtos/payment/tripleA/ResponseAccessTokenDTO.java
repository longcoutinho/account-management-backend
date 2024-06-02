package com.example.demo.dtos.payment.tripleA;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseAccessTokenDTO {
    String access_token;
    String token_type;
    Long expires_in;
    String message;
}
