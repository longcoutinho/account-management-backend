package com.example.demo.repositories.tables.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDER")
public class SaleOrderEntity implements Serializable {
    @Id
    @Column(name = "ID")
    String id;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "ITEM_ID")
    Long itemId;

    @Column(name = "AMOUNT")
    Long amount;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "PAYMENT_REFERENCE")
    String paymentReference;
}
