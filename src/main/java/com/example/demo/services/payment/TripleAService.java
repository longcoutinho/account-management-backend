package com.example.demo.services.payment;

import com.example.demo.dtos.payment.tripleA.RequestPaymentDTO;
import com.example.demo.dtos.payment.tripleA.ResponseAccessTokenDTO;
import com.example.demo.dtos.payment.tripleA.ResponseDetailPaymentDTO;
import com.example.demo.dtos.payment.tripleA.ResponsePaymentDTO;
import com.example.demo.repositories.tables.entities.SaleOrderEntity;
import com.example.demo.utils.constants.FnCommon;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TripleAService {
    @Value("${triple-a.auth.url}")
    String authUrl;

    @Value("${triple-a.payment.url}")
    String paymentUrl;

    @Value("${triple-a.client-id}")
    String client_id;

    @Value("${triple-a.client-secret}")
    String client_secret;

    @Value("${triple-a.grant-type}")
    String grant_type;

    @Value("${CANCEL_URL}")
    String cancelUrl;

    @Value("${RETURN_URL}")
    String successUrl;

    public ResponseAccessTokenDTO getAccessToken() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("client_id", client_id);
        params.put("client_secret", client_secret);
        params.put("grant_type", grant_type);

        String res = FnCommon.doGetRequest(authUrl, params, null);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(res, ResponseAccessTokenDTO.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponsePaymentDTO createPayment(SaleOrderEntity saleOrder) throws IOException {
        RequestPaymentDTO requestBody = new RequestPaymentDTO();
        requestBody.setType("triplea");
        requestBody.setMerchant_key("mkey-cltzq8mtk0i8f2nisdy8124zp");
        requestBody.setOrder_currency("VND");
        requestBody.setOrder_amount(saleOrder.getAmount());
        requestBody.setPayer_id("minhbn.gm@gmail.com");
        requestBody.setCancel_url(cancelUrl);
        requestBody.setSuccess_url(successUrl);
        ResponseAccessTokenDTO token = getAccessToken();

        String response = FnCommon.doPostRequest(paymentUrl, token.getAccess_token(), requestBody);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, ResponsePaymentDTO.class);
    }

    public ResponseDetailPaymentDTO getDetailPayment(String paymentReference) {
        ResponseAccessTokenDTO token = getAccessToken();
        String response = FnCommon.doPostRequest(paymentUrl + '/' + paymentReference , token.getAccess_token(), null);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, ResponseDetailPaymentDTO.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
