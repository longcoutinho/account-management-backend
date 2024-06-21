package com.example.demo.repositories.tables;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_FEE")
public class ProductFeeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "PRODUCT_ID")
    Long productId;

    @Column(name = "PRICE")
    Long price;

    @Column(name = "PAYMENT_CODE")
    String paymentCode;
}
