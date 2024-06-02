package com.example.demo.services.tables;

import com.example.demo.repositories.tables.PaymentMethodRepositoryJPA;
import com.example.demo.repositories.tables.entities.PaymentMethodEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceJPA {
    @Autowired
    PaymentMethodRepositoryJPA paymentMethodRepositoryJPA;

    public Object getAll() {
        return paymentMethodRepositoryJPA.getAll();
    }

    public PaymentMethodEntity findById(Long paymentMethodId) {
        // TODO Auto-generated method stub
        return paymentMethodRepositoryJPA.findById(paymentMethodId).get();
    }
}
