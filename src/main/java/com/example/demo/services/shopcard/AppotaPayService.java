package com.example.demo.services.shopcard;

import com.example.demo.services.security.jwt.JwtTokenProvider;
import com.example.demo.utils.constants.FnCommon;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AppotaPayService {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Value("${appota-pay.partner-code}")
    String partnerCode;

    @Value("${appota-pay.api-key}")
    String apiKey;

    @Value("${appota-pay.secret-key}")
    String secretKey;

    public String genAccessToken() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("cty", "appotapay-api;v=1");
        Map<String, Object> payload = new HashMap<>();
        payload.put("iss", partnerCode);
        payload.put("jti", apiKey + '-' + "5014225624");
        payload.put("api_key", apiKey);
        payload.put("exp", "5014225624");
        return jwtTokenProvider.generateToken(header, FnCommon.toString(payload), secretKey, SignatureAlgorithm.HS512);
    }


}
