package com.example.demo.repositories.tables.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name = "PAYMENTS")
public class PaymentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "METHOD")
    String method;

    @Column(name = "STATUS")
    String status;

    @Column(name = "URL")
    String url;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "PRICE")
    Long price;

    @Column(name = "ORDER_ID")
    String orderId;

    public PaymentEntity(CreatePaymentDTO request) {
        this.status = Status.PENDING.name();
        this.createDate = new Date(System.currentTimeMillis());
        this.createUser = request.getUsername();
        this.price = request.getPrice();
        this.method = request.getPaymentCode();
        this.orderId = request.getOrderId();
    }

    public enum Status {
        PENDING,
        SUCCESS,
        FAILED
    }
}
