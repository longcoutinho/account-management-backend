package com.example.demo.services.tables;

import com.example.demo.services.payment.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.tables.PaymentRepositoryJPA;
import com.example.demo.repositories.tables.entities.PaymentEntity;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.utils.constants.Constants;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;

@Service
public class PaymentServiceJPA {
    @Autowired
    PaymentRepositoryJPA paymentRepositoryJPA;

    @Autowired
    PaymentMethodServiceJPA paymentMethodServiceJPA;

    @Autowired
    UserServiceJPA userServiceJPA;

    @Autowired
    StripeService stripeService;

    public PaymentEntity create(PaymentEntity paymentEntity) {
        switch (Constants.PaymentMethod.valueOf(paymentEntity.getMethod())) {
            case EP:
                return createEpointPayment(paymentEntity);
            case STP:
                return createStripePayment(paymentEntity);
            default:
                return null;
        }
    }

    private PaymentEntity createStripePayment(PaymentEntity paymentEntity) {
        PaymentEntity paymentEntity1 = stripeService.createPayment(paymentEntity);
        paymentRepositoryJPA.save(paymentEntity1);
        return paymentEntity1;
    }

    private PaymentEntity createEpointPayment(PaymentEntity paymentEntity) {
        UserEntity user = (UserEntity) userServiceJPA.findByUsername(paymentEntity.getCreateUser());
        if (user.getBalance() < paymentEntity.getPrice()) {
            throw new CustomException(ErrorApp.INSUFFICIENT_BALANCE);
        }
        user.setBalance(user.getBalance() - paymentEntity.getPrice());
        userServiceJPA.save(user);
        paymentEntity.setStatus(PaymentEntity.Status.SUCCESS.name());
        paymentRepositoryJPA.save(paymentEntity);
        return paymentEntity;
    }

    public PaymentEntity findById(Long paymentId) {
        // TODO Auto-generated method stub
        return paymentRepositoryJPA.findById(paymentId).get();
    }
}
