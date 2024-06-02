package com.example.demo.utils.constants;

import com.example.demo.dtos.topup.ResponseSendOTPLordMobile;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.TreeMap;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class FnCommon {
    public static String doPostRequest2(String url, String token, Object body) {
        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpResponse<String> doPostRequest(String url, String token, Map<String, String> params, Object body) {
        // Tạo một HttpClient
        HttpClient client = HttpClient.newHttpClient();
        String bodyStr = "";
        if (body != null) {
            bodyStr = body.toString();
        }

        try {
            // Tạo một HttpRequest.Builder
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("X-APPOTAPAY-AUTH", "Bearer " + token)
                    .POST(HttpRequest.BodyPublishers.ofString(bodyStr));

            // Thêm tham số vào request
//            for (Map.Entry<String, String> entry : params.entrySet()) {
//                requestBuilder.header(entry.getKey(), entry.getValue());
//            }

            // Tạo và gửi request
            HttpRequest request = requestBuilder.build();
            HttpResponse<String> response =  client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static String doPostRequestFormData(String url, Map<String, String> params, String token) {
        String encodedFormData = encodeFormData(params);
        HttpRequest.BodyPublisher requestBody = HttpRequest.BodyPublishers.ofString(encodedFormData);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(requestBody)
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doPostRequestWithCookie(String url, Map<String, String> params, String token, String cookie) {
        String encodedFormData = encodeFormData(params);
        HttpRequest.BodyPublisher requestBody = HttpRequest.BodyPublishers.ofString(encodedFormData);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Cookie", cookie)
                .POST(requestBody)
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> doPostRequestGetCookie(String url, Map<String, String> params, String token) {
        String encodedFormData = encodeFormData(params);
        HttpRequest.BodyPublisher requestBody = HttpRequest.BodyPublishers.ofString(encodedFormData);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(requestBody)
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseSendOTPLordMobile responseSendOTPLordMobile = objectMapper.readValue(response.body(), ResponseSendOTPLordMobile.class);
            System.out.println(response.body());
            if (responseSendOTPLordMobile.getError().getCode().equals(Constants.ERROR_CODE_SEND_OTP.SUCCESS)) {
                HttpHeaders headers = response.headers();
                Map<String, List<String>> headerMap = headers.map();
                List<String> setCookieValues = headerMap.get("Set-Cookie");
                System.out.println(setCookieValues);
                return setCookieValues;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doGetRequest(String url, String token, Map<String, String> params) {
        HttpClient httpClient = HttpClient.newHttpClient();

        // Construct the full URL with parameters
        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String urlString = urlBuilder.toString();
        urlString = urlString.substring(0, urlString.length() - 1); // Remove the trailing "&"
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.body();
        }
        catch (Exception e) {
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

    public static String toString(Map<String, ?> map) {
        StringBuilder mapAsString = new StringBuilder("{");
        for (String key : map.keySet()) {
            mapAsString.append(key + "=" + map.get(key) + ", ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
        return mapAsString.toString();
    }

    public static boolean isAdmin(HttpServletRequest httpServletRequest) {
        UserEntity userEntity = (UserEntity) httpServletRequest.getAttribute("userInfo");
        return userEntity.getRole().equals(UserEntity.Role.ADMIN.value);
    }

    public static String generateHmacSha256Signature(Map<String, String> parameters, String secretKey) {
        try {
            String dataString = convertHashMapToString(parameters);

            // Create a Mac instance with the HMAC-SHA256 algorithm
            Mac mac = Mac.getInstance("HmacSHA256");

            // Create a SecretKeySpec from the secret key
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

            // Initialize the Mac with the secret key
            mac.init(secretKeySpec);

            // Calculate the HMAC-SHA256 signature
            byte[] signature = mac.doFinal(dataString.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a Base64-encoded string
            return Base64.getEncoder().encodeToString(signature);
        } catch (Exception e) {
            throw new RuntimeException("Error generating HMAC-SHA256 signature", e);
        }
    }

    public static String convertHashMapToString(Map<String, String> parameters) {
        // Create a new TreeMap to sort the parameters by key
        Map<String, String> sortedParameters = new TreeMap<>(parameters);

        // Build the parameter string
        StringBuilder parameterString = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParameters.entrySet()) {
            if (parameterString.length() > 0) {
                parameterString.append("&");
            }
            parameterString.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());
        }

        return parameterString.toString();
    }
}
