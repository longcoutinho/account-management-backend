package com.example.demo.services.shopcard;

import com.example.demo.dtos.RequestBuyCardDTO;
import com.example.demo.services.security.jwt.JwtTokenProvider;
import com.example.demo.utils.constants.FnCommon;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        long timeNow = Instant.now().getEpochSecond();
        long tenMinutesLater = timeNow + (10 * 60);
        payload.put("jti", apiKey + '-' + timeNow);
        payload.put("api_key", apiKey);
        payload.put("exp", tenMinutesLater);
        return jwtTokenProvider.generateToken(header, FnCommon.toString(payload), secretKey, SignatureAlgorithm.HS512);
    }

    public void buyCard(RequestBuyCardDTO requestBuyCardDTO) {
        String url = "https://api.appotapay.com/api/v1/service/shopcard/buy";
        String token = genAccessToken();
        HashMap<String, String> params = new LinkedHashMap<>();
        params.put("partnerRefId", requestBuyCardDTO.getTransactionId());
        params.put("productCode", requestBuyCardDTO.getProductCode());
        params.put("quantity", requestBuyCardDTO.getQuantity());
        params.put("signature", FnCommon.generateHmacSha256Signature(params, secretKey));
        HttpResponse<String> response = FnCommon.doPostRequest(url, token, params, null);
        System.out.println(response.body());
    }
}
