package com.example.demo.repositories.tables.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "SALE_ORDER")
public class SaleOrderEntity implements Serializable {
    @Id
    @Column(name = "ID")
    String id;

    @Column(name = "USERNAME")
    String username;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "ITEM_ID")
    String itemId;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "ACCOUNT_ID")
    String accountId;
}
