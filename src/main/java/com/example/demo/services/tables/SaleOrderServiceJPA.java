package com.example.demo.services.tables;

import com.example.demo.dtos.payment.tripleA.ResponseDetailPaymentDTO;
import com.example.demo.dtos.saleorder.*;
import com.example.demo.repositories.tables.SaleOrderRepositoryJPA;
import com.example.demo.repositories.tables.entities.CardOrderEntity;
import com.example.demo.services.payment.TripleAService;
import com.example.demo.utils.constants.Constants;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class SaleOrderServiceJPA {
//     @Autowired
//     SaleOrderRepositoryJPA saleOrderRepositoryJPA;

//     @Autowired
//     TripleAService tripleAService;

//     public Object create(RequestCardDTO params) throws IOException {
//         // Create sale order
//         CardOrderEntity cardOrderEntity = new CardOrderEntity();
//         cardOrderEntity.setId(String.valueOf(UUID.randomUUID()));
// //        cardOrderEntity.setCreateUser(params.getCreateUser());
//         cardOrderEntity.setCreateDate(new Date(System.currentTimeMillis()));
// //        cardOrderEntity.setItemId(params.getItemId());
// //        cardOrderEntity.setAmount(params.getAmount());
//         cardOrderEntity.setStatus(Constants.SALE_ORDER_STATUS.CREATE);
//         // Create payment
//         return tripleAService.createPayment(saleOrderRepositoryJPA.save(cardOrderEntity));
//     }

//     public SaleOrderResponseSummaryDTO getAll(RequestCardDTO params) {
//         List<SaleOrderResponseDTO> res = new LinkedList<>();
//         List<CardOrderEntity> list = saleOrderRepositoryJPA.getAll();
//         Long amount = 0L;
//         for(CardOrderEntity cardOrderEntity : list) {
//             SaleOrderResponseDTO saleOrderResponseDTO = new SaleOrderResponseDTO();
//             saleOrderResponseDTO.setId(cardOrderEntity.getId());
//             saleOrderResponseDTO.setCreateDate(cardOrderEntity.getCreateDate());
//             saleOrderResponseDTO.setStatus(cardOrderEntity.getStatus());
//             res.add(saleOrderResponseDTO);
//         }
//         SaleOrderResponseSummaryDTO result = new SaleOrderResponseSummaryDTO();
//         result.setListSaleOrder(res);
//         result.setTotalRequest((long) res.size());
//         result.setTotalAmount(amount);
//         return result;
//     }

//     public ResponseProcessOrderDTO processOrder(RequestProcessOrderDTO request) {
//         CardOrderEntity cardOrderEntity = saleOrderRepositoryJPA.findById(request.getOrderId());
//         if (!validateOrder(request, cardOrderEntity)) throw new CustomException(ErrorApp.INVALID_ORDER);
//         ResponseDetailPaymentDTO orderDetail = tripleAService.getDetailPayment(request.getPaymentReference());
//         if (orderDetail.getStatus().equals("good")) {
//             cardOrderEntity.setStatus(Constants.SALE_ORDER_STATUS.SUCCESS);
//         }
//         else {
//             cardOrderEntity.setStatus(Constants.SALE_ORDER_STATUS.FAIL);
//         }
//         saleOrderRepositoryJPA.save(cardOrderEntity);
//         ResponseProcessOrderDTO response = new ResponseProcessOrderDTO();
//         response.setStatus(cardOrderEntity.getStatus());
//         return response;
//     }

//     private boolean validateOrder(RequestProcessOrderDTO request, CardOrderEntity cardOrderEntity) {
//         if (!cardOrderEntity.getPaymentReference().equals(request.getPaymentReference())) return false;
//         if (!cardOrderEntity.getCreateUser().equals(request.getCreateUser())) return false;
//         return true;
//     }
}
