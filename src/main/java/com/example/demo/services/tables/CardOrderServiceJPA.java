package com.example.demo.services.tables;

import com.example.demo.controllers.card.RequestAllOrder;
import com.example.demo.controllers.card.RequestCardInfoDTO;
import com.example.demo.dtos.CountAllOrderDTO;
import com.example.demo.dtos.RequestBuyCardDTO;
import com.example.demo.dtos.RequestOrderCardDTO;
import com.example.demo.dtos.card.CardOrderDTO;
import com.example.demo.dtos.payment.appotaPay.ResponseBuyCardDTO;
import com.example.demo.repositories.tables.CardOrderRepositoryJPA;
import com.example.demo.repositories.tables.entities.*;
import com.example.demo.services.payment.StripeService;
import com.example.demo.services.shopcard.AppotaPayService;
import com.example.demo.services.tables.item.*;
import com.example.demo.utils.constants.Constants;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @Autowired
    CardServiceJPA cardServiceJPA;

    @Autowired
    CardFeeServiceJPA cardFeeServiceJPA;

    @Autowired
    StripeService stripeService;

    public Object create(RequestOrderCardDTO request) {
        // Create card order
        CardOrderEntity order = new CardOrderEntity(request);

        // Tinh lai gia tien
        float totalPrice = 0;
        for (CardOrderDTO card : request.getCardInfo()) {
            CardFeeEntity cardFeeEntity = cardFeeServiceJPA.findByCardItemIdAndPaymentMethodCode(card.getCardId(), request.getPaymentMethodCode());
            totalPrice += cardFeeEntity.getPrice() * card.getQuantity();
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
                case STP:
                    if (!stripeService.checkPaymentSuccess(paymentEntity)) {
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
            cardOrderEntity.setMsgError(e.getMessage());
            cardOrderRepositoryJPA.save(cardOrderEntity);
            throw new Exception(e.getMessage());
        }
    }

    public ResponseAllOrder getAll(RequestAllOrder request) {
        Long page = (request.getPage() == null) ? 0 : request.getPage();
        Long pageSize = (request.getPageSize() == null) ? 10 : request.getPageSize();
        Pageable pageable = PageRequest.of(Math.toIntExact(page), Math.toIntExact(pageSize));
        List<Object> listData = cardOrderRepositoryJPA.findAllByRequest(request.getFromDate(), setEndOfDay(request.getToDate()),
                request.getStatus(), request.getUsername(), request.getTransId(), pageable);
        Long count = cardOrderRepositoryJPA.countTotalByRequest(request.getFromDate(), setEndOfDay(request.getToDate()),
                request.getStatus(), request.getUsername(), request.getTransId());
        Long toTalRevenue = cardOrderRepositoryJPA.countToTalRevenue(request.getFromDate(), setEndOfDay(request.getToDate()),
                request.getStatus(), request.getUsername(), request.getTransId());
        ResponseAllOrder response = new ResponseAllOrder();
        response.setCount(count);
        response.setTotalRevenue(toTalRevenue);
        response.setListData(listData);
        return response;
    }

    private static Date setEndOfDay(Date date) {
        if (date == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public List<CardInfoResponse> getDetail(String orderId, UserEntity user) {
        List<CardInfoResponse> res = new LinkedList<>();
        CardOrderEntity cardOrderEntity = cardOrderRepositoryJPA.findById(orderId).get();
        if (!cardOrderEntity.getCreateUser().equals(user.getUsername()) && user.getRole().equals("USER")) throw new CustomException(ErrorApp.ACCESS_DENIED);
        List<CardOrderDetailEntity> list = cardOrderDetailServiceJPA.findByCardOrderId(cardOrderEntity.getId());
        for(CardOrderDetailEntity cardOrderDetailEntity: list) {
            List<CardInfoEntity> cardInfoEntities = cardInfoServiceJPA.findByOrderDetailId(cardOrderDetailEntity.getId());
            CardInfoResponse cardInfoResponse = new CardInfoResponse();
            cardInfoResponse.setCards(CardInfoEntity.covertToListDTO(cardInfoEntities));
            cardInfoResponse.setCardItemId(cardOrderDetailEntity.getItemId().toString());
            CardItemEntity cardItemEntity = cardItemServiceJPA.findById(cardOrderDetailEntity.getItemId());
            CardEntity cardEntity = cardServiceJPA.findById(cardItemEntity.getCardId());
            cardInfoResponse.setCardName(cardEntity.getName());
            res.add(cardInfoResponse);
        }
        return res;
    }
}
