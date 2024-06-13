package com.example.demo.repositories.tables.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.example.demo.dtos.RequestOrderCardDTO;

@Data
@Entity
@Table(name = "CARD_ORDER_DETAIL")
public class CardOrderDetailEntity implements Serializable {
    @Id
    @Column(name = "ID")
    String id;

    @Column(name = "ITEM_ID")
    Long itemId;

    @Column(name = "AMOUNT")
    Integer amount;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "CARD_ORDER_ID")
    String cardOrderId;

    public CardOrderDetailEntity() {
        this.id = String.valueOf(UUID.randomUUID());
    }
}
