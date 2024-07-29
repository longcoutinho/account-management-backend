package com.example.demo.repositories.tables.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
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

    @Column(name = "REQUEST")
    String request;

    @Column(name = "PRICE")
    float price;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "vi_VN", timezone = "Asia/Ho_Chi_Minh")
    Date createDate;

    @Column(name = "STATUS")
    String status;

    @Column(name = "MSG_ERROR")
    String msgError;

    @Column(name = "IP")
    String ip;

    public CardOrderEntity(RequestOrderCardDTO request) {
        this.id = String.valueOf(UUID.randomUUID());
        this.createUser = request.getUserInfo().getUsername();
        this.createDate = new Date(System.currentTimeMillis());
        this.status = Status.PENDING.name();
        this.price = request.getTotalPrice();
        Gson gson = new Gson();
        this.request = gson.toJson(request.getCardInfo());
        this.ip = request.getIp_address();
    }

    public enum Status {
        PENDING,
        SUCCESS,
        FAILED
    }
}
