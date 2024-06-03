package com.example.demo.services.tables.item;

import com.example.demo.controllers.card.RequestCardInfoDTO;
import com.example.demo.dtos.CardDTO;
import com.example.demo.dtos.RequestOrderCardDTO;
import com.example.demo.dtos.card.CardOrderDTO;
import com.example.demo.repositories.tables.CardOrderRepositoryJPA;
import com.example.demo.repositories.tables.entities.CardItemEntity;
import com.example.demo.repositories.tables.entities.CardOrderEntity;
import com.example.demo.repositories.tables.entities.CreatePaymentDTO;
import com.example.demo.repositories.tables.entities.PaymentEntity;
import com.example.demo.repositories.tables.entities.PaymentMethodEntity;
import com.example.demo.services.tables.PaymentMethodServiceJPA;
import com.example.demo.services.tables.PaymentServiceJPA;
import com.example.demo.utils.constants.Constants;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Object create(RequestOrderCardDTO request) {
        // Create card order
        CardOrderEntity order = new CardOrderEntity(request);

        // Do payment 
        Long totalPrice = 0L;
        for (CardOrderDTO card : request.getCardInfo()) {
            CardItemEntity cardItemEntity = cardItemServiceJPA.findById(card.getCardId());
            totalPrice += cardItemEntity.getPrice() * card.getQuantity();
        }
        CreatePaymentDTO requestPayment = new CreatePaymentDTO(request.getPaymentMethodCode(),
                request.getUserInfo().getUsername(), totalPrice);
        PaymentEntity payment = new PaymentEntity(requestPayment);
        payment = paymentServiceJPA.create(payment);
        
        // Update card order
        order.setPaymentId(payment.getId());
        cardOrderRepositoryJPA.save(order);

        // Response 
        ResponseOrderCardDTO response = new ResponseOrderCardDTO();
        response.setReturnURL(payment.getUrl());
        response.setOrderId(order.getId());
        return response;
    }

    public Object getInfo(RequestCardInfoDTO request) {
        // Check payment status
        CardOrderEntity cardOrderEntity = cardOrderRepositoryJPA.findById(request.getOrderId()).get();
        PaymentEntity paymentEntity = paymentServiceJPA.findById(cardOrderEntity.getPaymentId());
        switch (Constants.PaymentMethod.valueOf(paymentEntity.getMethod())) {
            case EPOINT:
                if (!paymentEntity.getStatus().equals(PaymentEntity.Status.SUCCESS.name())) {
                    throw new CustomException(ErrorApp.INVALID_PAYMENT);
                }
                break;
            default:
                throw new CustomException(ErrorApp.INVALID_PAYMENT);
        }

        // Order card

        // Find card to order

        // Response
        return null;
    }
}
