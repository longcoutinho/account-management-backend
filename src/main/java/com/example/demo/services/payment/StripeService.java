package com.example.demo.services.payment;

import com.example.demo.dtos.StripeCreatePaymentRes;
import com.example.demo.repositories.tables.entities.PaymentEntity;
import com.example.demo.utils.constants.FnCommon;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    public PaymentEntity createPayment(PaymentEntity paymentEntity) {
        String url = "https://api.stripe.com/v1/payment_links";
        Map<String, String> params = new HashMap<>();
        params.put("line_items[0][price]", paymentEntity.getPrice().toString());
        params.put("line_items[0][quantity]", "1");
        String username = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";
        String password = "";
        String credentials = username + ":" + password;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        HttpResponse<String> response = FnCommon.doPostRequestFormData(url, params, authHeader);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            StripeCreatePaymentRes res = objectMapper.readValue(response.body(), StripeCreatePaymentRes.class);
            paymentEntity.setUrl(res.getUrl());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return paymentEntity;
    }
}
