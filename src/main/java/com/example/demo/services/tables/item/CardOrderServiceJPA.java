package com.example.demo.services.tables.item;

import com.example.demo.dtos.CardDTO;
import com.example.demo.dtos.RequestOrderCardDTO;
import com.example.demo.dtos.card.CardOrderDTO;
import com.example.demo.repositories.tables.CardOrderRepositoryJPA;
import com.example.demo.repositories.tables.entities.CardItemEntity;
import com.example.demo.repositories.tables.entities.CardOrderEntity;
import com.example.demo.repositories.tables.entities.CreatePaymentDTO;
import com.example.demo.repositories.tables.entities.PaymentEntity;
import com.example.demo.services.tables.PaymentServiceJPA;

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
        
        // Update card order
        order.setPaymentId(payment.getId());
        cardOrderRepositoryJPA.save(order);

        // Response 
        ResponseOrderCardDTO response = new ResponseOrderCardDTO();
        response.setReturnURL(payment.getUrl());
        response.setOrderId(order.getId());
        return response;
    }
}
