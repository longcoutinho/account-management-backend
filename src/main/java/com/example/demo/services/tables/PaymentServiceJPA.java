package com.example.demo.services.tables;

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

    public PaymentEntity create(PaymentEntity paymentEntity) {
        switch (Constants.PaymentMethod.valueOf(paymentEntity.getMethod())) {
            case EPOINT:
                return createEpointPayment(paymentEntity);        
            default:
                return null;
        }
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
