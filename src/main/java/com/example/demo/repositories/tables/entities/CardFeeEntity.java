package com.example.demo.repositories.tables.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CARD_FEE")
public class CardFeeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "CARD_ITEM_ID")
    Long cardItemId;

    @Column(name = "PAYMENT_METHOD_CODE")
    String paymentMethodCode;

    @Column(name = "PRICE")
    float price;
}
