package com.example.demo.services.shopcard;

import com.example.demo.dtos.payment.tripleA.ResponseAccessTokenDTO;
import com.example.demo.utils.constants.FnCommon;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AppotaPayService {
    @Value("${appota-pay.partner-code}")
    String partnerCode;

    @Value("${appota-pay.api-key}")
    String apiKey;

    @Value("${appota-pay.secret-key}")
    String secretKey;

    public String genAccessToken() {

    }
}
