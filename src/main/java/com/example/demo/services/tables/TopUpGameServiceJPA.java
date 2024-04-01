package com.example.demo.services.tables;

import com.example.demo.dtos.GetAllTopUpDTO;
import com.example.demo.dtos.ReportTopUp;
import com.example.demo.dtos.TopUpRequestDTO;
import com.example.demo.dtos.ResponsePaymentStatus;
import com.example.demo.dtos.payment.CreatePaymentLinkRequestBody;
import com.example.demo.dtos.topup.PaymentStatusRequestDTO;
import com.example.demo.dtos.topup.ResponseSendOTPLordMobile;
import com.example.demo.dtos.topup.TopUpResponseDTO;
import com.example.demo.repositories.tables.TopUpGameRepositoryJPA;
import com.example.demo.repositories.tables.entities.TopUpEntity;
import com.example.demo.services.payment.PayOSService;
import com.example.demo.utils.constants.Constants;
import com.example.demo.utils.constants.FnCommon;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TopUpGameServiceJPA {
    @Autowired
    TopUpGameRepositoryJPA topUpRepositoryJPA;

    @Autowired
    UserServiceJPA userServiceJPA;

    @Autowired
    PayOSService paymentService;

    @Value("${lord-mobile.send-otp}")
    String lordMobileSendOtpUrl;

    @Value("${RETURN_URL}")
    String returnURL;

    public Object createRequest(TopUpRequestDTO params) {
        // create TopUp request;
        TopUpEntity topUpEntity = new TopUpEntity();
        topUpEntity.setAmount(params.getAmount());
        topUpEntity.setStatus(0L);
        topUpEntity.setUsername(params.getUsername());
        topUpEntity.setMethod(params.getMethod());
        topUpEntity.setCreateDate(new Date(System.currentTimeMillis()));
        topUpRepositoryJPA.save(topUpEntity);
        // create Payment request;
        CreatePaymentLinkRequestBody req = new CreatePaymentLinkRequestBody();
        req.setDescription(params.getUsername() + " " + topUpEntity.getId());
        req.setReturnUrl(returnURL);
        req.setOrderCode(topUpEntity.getId());
        req.setPrice(params.getAmount());
        req.setUsername(params.getUsername());
        return paymentService.createTopUpRequest(req);
    }

    public Object confirm(TopUpRequestDTO params) {
        TopUpEntity topUpEntity = topUpRepositoryJPA.findById(params.getId()).get();
        topUpEntity.setStatus(1L); //success;
        topUpRepositoryJPA.save(topUpEntity);
        TopUpRequestDTO topUpRequestDTO = new TopUpRequestDTO();
        topUpRequestDTO.setAmount(topUpEntity.getAmount());
        topUpRequestDTO.setUsername(topUpEntity.getUsername());
        userServiceJPA.addBalance(topUpRequestDTO);
        return 1L;
    }

    public TopUpResponseDTO getAll(GetAllTopUpDTO params) {
        TopUpResponseDTO res = new TopUpResponseDTO();
        res.setListTopUp(topUpRepositoryJPA.getAll(params.getStatus(), params.getUsername(), params.getTransId()));
        res.setTotalRequest(report(params).getTotalRequest());
        res.setTotalAmount(report(params).getTotalAmount());
        return res;
    }

    public ReportTopUp report(GetAllTopUpDTO params) {
        ReportTopUp reportTopUp = new ReportTopUp();
        reportTopUp.setTotalAmount(topUpRepositoryJPA.getSumAmount(params.getStatus(), params.getUsername(), params.getTransId()));
        reportTopUp.setTotalRequest(topUpRepositoryJPA.getSumRequest(params.getStatus(), params.getUsername(), params.getTransId()));
        return reportTopUp;
    }

    public Object cancel(TopUpRequestDTO params) {
        TopUpEntity topUpEntity = topUpRepositoryJPA.findById(params.getId()).get();
        topUpEntity.setStatus(2L); //fail;
        topUpRepositoryJPA.save(topUpEntity);
        return 1L;
    }

    public Object getPaymentStatus(PaymentStatusRequestDTO params) {
        ResponsePaymentStatus response = (ResponsePaymentStatus) paymentService.getOrderById(Math.toIntExact(params.getOrderCode()));
        if (response.getStatus().equals("PAID")) {
            confirm(new TopUpRequestDTO(response.getOrderCode()));
        }
        return 1L;
    }

    public Object sendOtpLordMobile(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("game_id", "1051029902");
        params.put("user_id", id);
        String res = FnCommon.doGetRequest(lordMobileSendOtpUrl, null, params);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ResponseSendOTPLordMobile responseSendOTPLordMobile = objectMapper.readValue(res, ResponseSendOTPLordMobile.class);
            String errorCode = responseSendOTPLordMobile.getError().getCode();
            if (errorCode.equals(Constants.ERROR_CODE_SEND_OTP.TOO_MANY_REQUEST)) {
                throw new CustomException(ErrorApp.TOO_MANY_REQUEST);
            }
            else if (errorCode.equals(Constants.ERROR_CODE_SEND_OTP.IGG_ID_INVALID)) {
                throw new CustomException(ErrorApp.IGG_INVALID_ID);
            }
            return 1L;
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
