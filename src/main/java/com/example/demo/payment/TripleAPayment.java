package com.example.demo.payment;

import com.example.demo.dtos.SaleOrderDTO;
import com.example.demo.dtos.payment.tripleA.RequestPaymentDTO;
import com.example.demo.dtos.payment.tripleA.ResponseAccessTokenDTO;
import com.example.demo.dtos.payment.tripleA.ResponsePaymentDTO;
import com.example.demo.repositories.tables.entities.SaleOrderEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TripleAPayment {
    @Value("${triple-a.auth.url}")
    String authUrl;

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

    public ResponseAccessTokenDTO getAccessToken() throws IOException {
        String url = authUrl; // URL của yêu cầu POST
        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("client_id", client_id);
        formData.put("client_secret", client_secret);
        formData.put("grant_type", grant_type);
        // Create the form data as a URL-encoded string
        String encodedFormData = encodeFormData(formData);
        // Create the request body publisher
        HttpRequest.BodyPublisher requestBody = HttpRequest.BodyPublishers.ofString(encodedFormData);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(requestBody)
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseAccessTokenDTO responseAccessTokenDTO = objectMapper.readValue(response.body(), ResponseAccessTokenDTO.class);
            return responseAccessTokenDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponsePaymentDTO createPayment(SaleOrderDTO saleOrder) {
        RequestPaymentDTO request = new RequestPaymentDTO();
        request.setType("triplea");
        request.setMerchant_key("mkey-cltzq8mtk0i8f2nisdy8124zp");
        request.setOrder_currency("VND");
        request.setOrder_amount(saleOrder.getAmount());
        request.setPayer_id("minhbn.gm@gmail.com");
        request.setOrder_id(saleOrder.getId());
        request.setCancel_url(cancelUrl);
        request.setSuccess_url(successUrl);
        return null;
    }

    private static String encodeFormData(Map<String, String> formData) {
        StringBuilder encodedForm = new StringBuilder();
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            if (encodedForm.length() > 0) {
                encodedForm.append("&");
            }
            encodedForm.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            encodedForm.append("=");
            encodedForm.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return encodedForm.toString();
    }
}
