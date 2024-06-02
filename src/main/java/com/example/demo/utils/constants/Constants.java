package com.example.demo.utils.constants;

import com.example.demo.dtos.payment.tripleA.ResponsePaymentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Constants {
    public interface SALE_ORDER_STATUS {
        Long CREATE = 0L;
        Long SUCCESS = 1L;
        Long FAIL = 2L;
    }

    public interface ERROR_CODE_SEND_OTP {
        String TOO_MANY_REQUEST = "216";
        String IGG_ID_INVALID = "202";
        String SUCCESS = "0";
    }

    public enum LoginMethod {
        DIRECT(1),
        FACEBOOK(2),
        GOOGLE(3),
        X(4),
        TELEGRAM(5);

        final int value;
        LoginMethod(int val) {
            this.value = val;
        }
    }

    public enum PaymentMethod {
        EPOINT(1);

        final int value;
        PaymentMethod(int val) {
            this.value = val;
        }
    }
}