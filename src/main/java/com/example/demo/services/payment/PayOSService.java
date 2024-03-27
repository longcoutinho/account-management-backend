package com.example.demo.services.payment;

import com.example.demo.dtos.ResponsePaymentStatus;
import com.example.demo.dtos.payment.CreatePaymentLinkRequestBody;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lib.payos.PayOS;
import com.lib.payos.type.ItemData;
import com.lib.payos.type.PaymentData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PayOSService {
    private final PayOS payOS;

    public PayOSService(PayOS payOS) {
        this.payOS = payOS;
    }

    public Object createTopUpRequest(CreatePaymentLinkRequestBody params) {
        try {
            // Add item
            String itemName = "Nạp tiền cho tài khoản " + params.getUsername();
            ItemData item = new ItemData(itemName, 1, Math.toIntExact(params.getPrice()));
            List<ItemData> itemList = new ArrayList<ItemData>();
            itemList.add(item);
            // create payment link
            PaymentData paymentData = new PaymentData(Math.toIntExact(params.getOrderCode()), Math.toIntExact(params.getPrice()), params.getDescription(),
                    itemList, params.getCancelUrl(), params.getReturnUrl());
            JsonNode data = payOS.createPaymentLink(paymentData);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResponsePaymentStatus getOrderById(int orderId) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode order = payOS.getPaymentLinkInfomation(orderId);
            ResponsePaymentStatus response = objectMapper.treeToValue(order, ResponsePaymentStatus.class);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
