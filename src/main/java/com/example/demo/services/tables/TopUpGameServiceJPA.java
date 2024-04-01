package com.example.demo.services.tables;

import com.example.demo.dtos.GetAllTopUpDTO;
import com.example.demo.dtos.ReportTopUp;
import com.example.demo.dtos.TopUpRequestDTO;
import com.example.demo.dtos.ResponsePaymentStatus;
import com.example.demo.dtos.payment.CreatePaymentLinkRequestBody;
import com.example.demo.dtos.topup.PaymentStatusRequestDTO;
import com.example.demo.dtos.topup.RequestTokenLordMobile;
import com.example.demo.dtos.topup.TopUpResponseDTO;
import com.example.demo.repositories.tables.TopUpGameRepositoryJPA;
import com.example.demo.repositories.tables.entities.TopUpEntity;
import com.example.demo.services.payment.PayOSService;
import com.example.demo.services.topupgame.LordMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TopUpGameServiceJPA {
    @Autowired
    TopUpGameRepositoryJPA topUpRepositoryJPA;

    @Autowired
    UserServiceJPA userServiceJPA;

    @Autowired
    PayOSService paymentService;

    @Autowired
    LordMobileService lordMobileService;

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
        return lordMobileService.sendOtpLordMobile(id);
    }

    public Object getTokenLordMobile(String id, RequestTokenLordMobile params) {
        return lordMobileService.getAccessToken(id, params);
    }
}
