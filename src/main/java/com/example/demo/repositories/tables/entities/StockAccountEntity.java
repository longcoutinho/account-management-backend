package com.example.demo.repositories.tables.entities;

import com.example.demo.dtos.ResponseUserDTO;
import com.example.demo.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "STOCK_ACCOUNT")
public class StockAccountEntity implements Serializable {
    @Id
    @Column(name = "ID")
    String id;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "USERNAME")
    String username;

    @Column(name = "PASSWORD")
    String password;

    @Column(name = "ITEM_ID")
    String itemId;
}
