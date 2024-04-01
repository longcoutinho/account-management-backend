package com.example.demo.dtos.topup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponseSendOTPLordMobile {
    ErrorSendOTP error;
}
