package com.example.demo.services.tables;

import com.example.demo.dtos.*;
import com.example.demo.dtos.payment.tripleA.ResponseDetailPaymentDTO;
import com.example.demo.dtos.saleorder.*;
import com.example.demo.repositories.tables.SaleOrderRepositoryJPA;
import com.example.demo.repositories.tables.entities.SaleOrderEntity;
import com.example.demo.services.payment.TripleAService;
import com.example.demo.utils.constants.Constants;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class SaleOrderServiceJPA {
    @Autowired
    SaleOrderRepositoryJPA saleOrderRepositoryJPA;

    @Autowired
    TripleAService tripleAService;

    public Object create(SaleOrderDTO params) throws JsonProcessingException {
        // Create sale order
        SaleOrderEntity saleOrderEntity = new SaleOrderEntity();
        saleOrderEntity.setId(String.valueOf(UUID.randomUUID()));
        saleOrderEntity.setCreateUser(params.getCreateUser());
        saleOrderEntity.setCreateDate(new Date(System.currentTimeMillis()));
        saleOrderEntity.setItemId(params.getItemId());
        saleOrderEntity.setAmount(params.getAmount());
        saleOrderEntity.setStatus(Constants.SALE_ORDER_STATUS.CREATE);
        // Create payment
        return tripleAService.createPayment(saleOrderRepositoryJPA.save(saleOrderEntity));
    }

    public SaleOrderResponseSummaryDTO getAll(SaleOrderDTO params) {
        List<SaleOrderResponseDTO> res = new LinkedList<>();
        List<SaleOrderEntity> list = saleOrderRepositoryJPA.getAll();
        Long amount = 0L;
        for(SaleOrderEntity saleOrderEntity: list) {
            SaleOrderResponseDTO saleOrderResponseDTO = new SaleOrderResponseDTO();
            saleOrderResponseDTO.setId(saleOrderEntity.getId());
            saleOrderResponseDTO.setCreateDate(saleOrderEntity.getCreateDate());
            saleOrderResponseDTO.setStatus(saleOrderEntity.getStatus());
            res.add(saleOrderResponseDTO);
        }
        SaleOrderResponseSummaryDTO result = new SaleOrderResponseSummaryDTO();
        result.setListSaleOrder(res);
        result.setTotalRequest((long) res.size());
        result.setTotalAmount(amount);
        return result;
    }

    public ResponseProcessOrderDTO processOrder(RequestProcessOrderDTO request) {
        SaleOrderEntity saleOrderEntity = saleOrderRepositoryJPA.findById(request.getOrderId());
        if (!validateOrder(request, saleOrderEntity)) throw new CustomException(ErrorApp.INVALID_ORDER);
        ResponseDetailPaymentDTO orderDetail = tripleAService.getDetailPayment(request.getPaymentReference());
        if (orderDetail.getStatus().equals("good")) {
            saleOrderEntity.setStatus(Constants.SALE_ORDER_STATUS.SUCCESS);
        }
        else {
            saleOrderEntity.setStatus(Constants.SALE_ORDER_STATUS.FAIL);
        }
        saleOrderRepositoryJPA.save(saleOrderEntity);
        ResponseProcessOrderDTO response = new ResponseProcessOrderDTO();
        response.setStatus(saleOrderEntity.getStatus());
        return response;
    }

    private boolean validateOrder(RequestProcessOrderDTO request, SaleOrderEntity saleOrderEntity) {
        if (!saleOrderEntity.getPaymentReference().equals(request.getPaymentReference())) return false;
        if (!saleOrderEntity.getCreateUser().equals(request.getCreateUser())) return false;
        return true;
    }
}
