package com.example.demo.services.shopcard;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
        return generateToken(header, payload, secretKey);
    }

    public void buyCard(RequestBuyCardDTO request) {
        String url = "https://api.appotapay.com/api/v1/service/shopcard/buy";
        String token = genAccessToken();
        HashMap<String, String> params = new LinkedHashMap<>();
        params.put("partnerRefId", request.getTransactionId());
        params.put("productCode", request.getProductCode());
        params.put("quantity", request.getQuantity());
        request.setSignature(FnCommon.generateHmacSha256Signature(params, secretKey));
        HttpResponse<String> response = FnCommon.doPostRequest(url, token, null, request);
        System.out.println(response.body());
    }

    public static String generateToken(Map<String, Object> header, Map<String, Object> payload, String secretKey) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withHeader(header)
                    .withPayload(payload)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
