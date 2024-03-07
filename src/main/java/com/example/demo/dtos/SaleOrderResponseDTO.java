package com.example.demo.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class SaleOrderResponseDTO {
    String id;
    Date createDate;
    Long status;
    String username;
    String password;
    String userId;
    String itemName;
    Long price;
}
