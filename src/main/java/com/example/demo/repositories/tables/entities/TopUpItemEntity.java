package com.example.demo.repositories.tables.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TOP_UP_ITEM")
public class TopUpItemEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "image")
    String image;

    @Column(name = "amount")
    Long amount;

    @Column(name = "price")
    Long price;

    @Column(name = "currency")
    String currency;

    @Column(name = "game_id")
    Long gameId;
}
