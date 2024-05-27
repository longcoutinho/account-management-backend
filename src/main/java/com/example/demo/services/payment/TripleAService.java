package com.example.demo.services.payment;

import com.example.demo.dtos.payment.tripleA.RequestPaymentDTO;
import com.example.demo.dtos.payment.tripleA.ResponseAccessTokenDTO;
import com.example.demo.dtos.payment.tripleA.ResponseDetailPaymentDTO;
import com.example.demo.dtos.payment.tripleA.ResponsePaymentDTO;
import com.example.demo.repositories.tables.entities.CardItemEntity;
import com.example.demo.repositories.tables.entities.CardOrderEntity;
import com.example.demo.services.tables.item.CardItemServiceJPA;
import com.example.demo.utils.constants.FnCommon;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TripleAService {
    @Autowired
    CardItemServiceJPA cardItemServiceJPA;

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
        params.put("client_id", "oacid-clu1dpq2m0kwyrhis6fm0gisq");
        params.put("client_secret", "924c2d167a9ac6bbb2cfe041185ef4c68192b7758f3e9bb654c4dff80cc4ad27");
        params.put("grant_type", "client_credentials");

        String res = FnCommon.doPostRequestFormData(authUrl, params, null);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(res, ResponseAccessTokenDTO.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponsePaymentDTO createPayment(CardOrderEntity saleOrder) {
        CardItemEntity cardItemEntity = cardItemServiceJPA.findById(saleOrder.getItemId());
        RequestPaymentDTO requestBody = new RequestPaymentDTO();
        requestBody.setType("triplea");
        requestBody.setMerchant_key("mkey-cltzq8mtk0i8f2nisdy8124zp");
        requestBody.setOrder_currency("VND");
        requestBody.setOrder_amount(saleOrder.getAmount() * cardItemEntity.getPrice());
        requestBody.setPayer_id("minhbn.gm@gmail.com");
        requestBody.setCancel_url(cancelUrl);
        requestBody.setSuccess_url(successUrl);
        ResponseAccessTokenDTO token = getAccessToken();
        String access_token = token != null ? token.getAccess_token() : "";
        System.out.println(requestBody);
        String response = FnCommon.doPostRequest2(paymentUrl, access_token, requestBody);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
           return objectMapper.readValue(response, ResponsePaymentDTO.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseDetailPaymentDTO getDetailPayment(String paymentReference) {
        ResponseAccessTokenDTO token = getAccessToken();
        String response = FnCommon.doPostRequest2(paymentUrl + '/' + paymentReference , token.getAccess_token(), null);
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
