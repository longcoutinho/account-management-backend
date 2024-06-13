package com.example.demo.services.tables;

import com.example.demo.controllers.card.RequestCardInfoDTO;
import com.example.demo.dtos.RequestBuyCardDTO;
import com.example.demo.dtos.RequestOrderCardDTO;
import com.example.demo.dtos.card.CardOrderDTO;
import com.example.demo.dtos.payment.appotaPay.ResponseBuyCardDTO;
import com.example.demo.repositories.tables.CardOrderRepositoryJPA;
import com.example.demo.repositories.tables.entities.*;
import com.example.demo.services.shopcard.AppotaPayService;
import com.example.demo.services.tables.item.*;
import com.example.demo.utils.constants.Constants;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardOrderServiceJPA {
    @Autowired
    CardOrderRepositoryJPA cardOrderRepositoryJPA;

    @Autowired
    PaymentServiceJPA paymentServiceJPA;

    @Autowired
    CardItemServiceJPA cardItemServiceJPA;

    @Autowired
    PaymentMethodServiceJPA paymentMethodServiceJPA;

    @Autowired
    AppotaPayService appotaPayService;

    @Autowired
    CardOrderDetailServiceJPA cardOrderDetailServiceJPA;

    @Autowired
    CardInfoServiceJPA cardInfoServiceJPA;

    public Object create(RequestOrderCardDTO request) {
        // Create card order
        CardOrderEntity order = new CardOrderEntity(request);

        // Do payment
        // Tinh lai gia tien
        Long totalPrice = 0L;
        for (CardOrderDTO card : request.getCardInfo()) {
            CardItemEntity cardItemEntity = cardItemServiceJPA.findById(card.getCardId());
            totalPrice += cardItemEntity.getPrice() * card.getQuantity();
        }
        order.setPrice(totalPrice);
        cardOrderRepositoryJPA.save(order);
        // Create payment
        CreatePaymentDTO requestPayment = new CreatePaymentDTO(request.getPaymentMethodCode(),
                request.getUserInfo().getUsername(), totalPrice, order.getId());
        PaymentEntity payment = new PaymentEntity(requestPayment);
        payment = paymentServiceJPA.create(payment);

        // Response
        ResponseOrderCardDTO response = new ResponseOrderCardDTO();
        response.setReturnURL(payment.getUrl());
        response.setOrderId(order.getId());
        return response;
    }

    public List<CardInfoResponse> getInfo(RequestCardInfoDTO request, String username) throws Exception {
        CardOrderEntity cardOrderEntity = cardOrderRepositoryJPA.findById(request.getOrderId()).get();
        if (!cardOrderEntity.getCreateUser().equals(username)) throw new CustomException(ErrorApp.ORDER_CARD_FAILED);
        if (!(cardOrderEntity.getStatus()).equals(CardOrderEntity.Status.PENDING.name())) throw new CustomException(ErrorApp.ORDER_CARD_FAILED);
        try {
            List<CardInfoResponse> res = new LinkedList<>();
            /** CHECK PAYMENT STATUS **/
            PaymentEntity paymentEntity = paymentServiceJPA.findByOrderId(request.getOrderId());
            switch (Constants.PaymentMethod.valueOf(paymentEntity.getMethod())) {
                case EP:
                    if (!paymentEntity.getStatus().equals(PaymentEntity.Status.SUCCESS.name())) {
                        throw new CustomException(ErrorApp.INVALID_PAYMENT);
                    }
                    break;
                default:
                    throw new CustomException(ErrorApp.INVALID_PAYMENT);
            }
            /** ORDER CARD **/
            // Create card order detail for each order
            ObjectMapper objectMapper = new ObjectMapper();
            List<CardOrderDTO> cardList = objectMapper.readValue(cardOrderEntity.getRequest(), new TypeReference<List<CardOrderDTO>>() {});
            for(CardOrderDTO cardInfo: cardList) {
                CardOrderDetailEntity cardOrderDetailEntity = new CardOrderDetailEntity();
                cardOrderDetailEntity.setItemId(cardInfo.getCardId());
                cardOrderDetailEntity.setAmount(cardInfo.getQuantity());
                cardOrderDetailEntity.setCreateDate(new Date(System.currentTimeMillis()));
                cardOrderDetailEntity.setCardOrderId(request.getOrderId());
                CardOrderDetailEntity savedDetail = cardOrderDetailServiceJPA.save(cardOrderDetailEntity);
                // request to appota
                CardItemEntity cardItemEntity = cardItemServiceJPA.findById(cardInfo.getCardId());
                RequestBuyCardDTO requestBuyCardDTO = new RequestBuyCardDTO(savedDetail.getId(), cardItemEntity.getCode(), cardInfo.getQuantity().toString());
                ResponseBuyCardDTO responseBuyCardDTO = appotaPayService.buyCard(requestBuyCardDTO);
                // Save to card info
                for(CardInfo responseCard: responseBuyCardDTO.getCards()) {
                    CardInfoEntity cardInfoEntity = new CardInfoEntity();
                    cardInfoEntity.setOrderDetailId(savedDetail.getId());
                    cardInfoEntity.setItemId(cardInfo.getCardId());
                    cardInfoEntity.setCode(responseCard.getCode());
                    cardInfoEntity.setSerial(responseCard.getSerial());
                    cardInfoEntity.setVendor(responseCard.getVendor());
                    cardInfoEntity.setValue(responseCard.getValue());
                    cardInfoEntity.setExpiry(responseCard.getExpiry().toString());
                    cardInfoEntity.setCreateDate(new Date(System.currentTimeMillis()));
                    cardInfoServiceJPA.save(cardInfoEntity);
                }
                CardInfoResponse cardInfoResponse = new CardInfoResponse();
                cardInfoResponse.setCards(responseBuyCardDTO.getCards());
                cardInfoResponse.setCardItemId(cardOrderDetailEntity.getCardOrderId());
                res.add(cardInfoResponse);
            }
            cardOrderEntity.setStatus(CardOrderEntity.Status.SUCCESS.name());
            cardOrderRepositoryJPA.save(cardOrderEntity);
            // Response
            return res;
        }
        catch (Exception e) {
            cardOrderEntity.setStatus(CardOrderEntity.Status.FAILED.name());
            cardOrderRepositoryJPA.save(cardOrderEntity);
            throw new Exception(e.getMessage());
        }
    }

    public Object getAll(String username) {
        return cardOrderRepositoryJPA.findByCreateUser(username);
    }
}
