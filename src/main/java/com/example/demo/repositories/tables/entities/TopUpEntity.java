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
@Table(name = "TOP_UP")
public class TopUpEntity implements Serializable {
    @Id
    @Column(name = "ID")
    String id;

    @Column(name = "AMOUNT")
    Long amount;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "USER_ID")
    String userId;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "METHOD")
    Long method;
}
