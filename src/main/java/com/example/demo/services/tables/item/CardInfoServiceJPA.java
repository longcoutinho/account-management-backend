package com.example.demo.services.tables.item;

import com.example.demo.controllers.card.RequestCardInfoDTO;
import com.example.demo.dtos.RequestBuyCardDTO;
import com.example.demo.dtos.RequestOrderCardDTO;
import com.example.demo.dtos.card.CardOrderDTO;
import com.example.demo.dtos.payment.appotaPay.ResponseBuyCardDTO;
import com.example.demo.repositories.tables.CardOrderRepositoryJPA;
import com.example.demo.repositories.tables.entities.*;
import com.example.demo.services.decryption.RSADecryption;
import com.example.demo.services.shopcard.AppotaPayService;
import com.example.demo.services.tables.CardOrderDetailServiceJPA;
import com.example.demo.services.tables.PaymentMethodServiceJPA;
import com.example.demo.services.tables.PaymentServiceJPA;
import com.example.demo.utils.constants.Constants;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardInfoServiceJPA {
    @Autowired
    CardInfoRepositoryJPA cardInfoRepositoryJPA;

    public void save(CardInfoEntity cardInfoEntity) {
        cardInfoRepositoryJPA.save(cardInfoEntity);
    }
}
