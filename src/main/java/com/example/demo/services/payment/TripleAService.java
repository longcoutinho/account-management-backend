package com.example.demo.services.payment;

import com.example.demo.dtos.payment.tripleA.RequestPaymentDTO;
import com.example.demo.dtos.payment.tripleA.ResponseAccessTokenDTO;
import com.example.demo.dtos.payment.tripleA.ResponseDetailPaymentDTO;
import com.example.demo.dtos.payment.tripleA.ResponsePaymentDTO;
import com.example.demo.repositories.tables.entities.SaleOrderEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    public ResponsePaymentDTO createPayment(SaleOrderEntity saleOrder) throws JsonProcessingException {
        RequestPaymentDTO requestBody = new RequestPaymentDTO();
        requestBody.setType("triplea");
        requestBody.setMerchant_key("mkey-cltzq8mtk0i8f2nisdy8124zp");
        requestBody.setOrder_currency("VND");
        requestBody.setOrder_amount(saleOrder.getAmount());
        requestBody.setPayer_id("minhbn.gm@gmail.com");
        requestBody.setCancel_url(cancelUrl);
        requestBody.setSuccess_url(successUrl);
        ObjectMapper objectMapper = new ObjectMapper();

        String url = paymentUrl;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ResponsePaymentDTO responsePaymentDTO = objectMapper.readValue(response.body(), ResponsePaymentDTO.class);
            return responsePaymentDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseDetailPaymentDTO getDetailPayment(String paymentReference) {
        String url = paymentUrl + '/' + paymentReference;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)) // Specify the URL you want to send the GET request to
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDetailPaymentDTO responseDetailPaymentDTO = objectMapper.readValue(response.body(), ResponseDetailPaymentDTO.class);
            return responseDetailPaymentDTO;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
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
