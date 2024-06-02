package com.example.demo.repositories.tables.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.example.demo.dtos.RequestOrderCardDTO;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CARD_ORDERS")
public class CardOrderEntity implements Serializable {
    @Id
    @Column(name = "ID")
    String id;

    @Column(name = "PAYMENT_ID")
    Long paymentId;

    @Column(name = "CARD_ORDER_ID")
    Long cardOrderId;

    @Column(name = "PRICE")
    Long price;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "STATUS")
    String status;

    @Column(name = "MSG_ERROR")
    Long msgError;

    public CardOrderEntity(RequestOrderCardDTO request) {
        this.id = String.valueOf(UUID.randomUUID());
        this.createUser = request.getUserInfo().getUsername();
        this.createDate = new Date(System.currentTimeMillis());
        this.status = Status.PENDING.name();
    }

    public enum Status {
        PENDING,
        SUCCESS,
        FAILED
    }
}
