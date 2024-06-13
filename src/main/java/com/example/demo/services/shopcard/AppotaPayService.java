package com.example.demo.services.shopcard;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.dtos.RequestBuyCardDTO;
import com.example.demo.dtos.payment.appotaPay.ResponseBuyCardDTO;
import com.example.demo.services.decryption.RSADecryption;
import com.example.demo.services.security.jwt.JwtTokenProvider;
import com.example.demo.services.tables.item.CardInfo;
import com.example.demo.utils.constants.FnCommon;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import com.fasterxml.jackson.databind.*;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    public ResponseBuyCardDTO buyCard(RequestBuyCardDTO request) {
        String url = "https://api.appotapay.com/api/v1/service/shopcard/buy";
        String token = genAccessToken();
        System.out.println(token);
        HashMap<String, String> params = new LinkedHashMap<>();
        params.put("partnerRefId", request.getPartnerRefId());
        params.put("productCode", request.getProductCode());
        params.put("quantity", request.getQuantity());
        request.setSignature(FnCommon.generateHmacSha256Signature(params, secretKey));
        try {
            HttpResponse<String> response = FnCommon.doPostRequest(url, token, null, request);
            if (response.statusCode() != HttpStatus.OK.value()) {
                throw new CustomException(ErrorApp.ORDER_CARD_FAILED);
            }
//            String body = "{\"errorCode\":0,\"message\":\"Th\\u00e0nh c\\u00f4ng\",\"cards\":[{\"serial\":\"20000277202666\",\"code\":\"724889714164747\",\"value\":10000,\"vendor\":\"viettel\",\"expiry\":\"31-12-2024\"}],\"transaction\":{\"appotapayTransId\":\"01J04PGFAY3PGHJM116JVVGQHE\",\"amount\":9800,\"time\":\"12-06-2024 05:25:44\"}}";
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ResponseBuyCardDTO responseBuyCardDTO = objectMapper.readValue(response.body(), ResponseBuyCardDTO.class);
            for(CardInfo cardInfo: responseBuyCardDTO.getCards()) {
                cardInfo.setCode(RSADecryption.decryptCard(cardInfo.getCode()));
            }
            return responseBuyCardDTO;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
