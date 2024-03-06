package com.example.demo.repositories.tables.entities;

import com.example.demo.dtos.ResponseUserDTO;
import com.example.demo.dtos.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TOP_UP")
public class TopUpEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "AMOUNT")
    Long amount;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "USERNAME")
    String username;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "METHOD")
    Long method;
}
