package com.example.demo.repositories.tables.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CARD_ITEM")
public class ItemEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "PRICE")
    Long price;

    @Column(name = "CARD_ID")
    Long cardId;

    @Column(name = "CODE")
    String code;
}
