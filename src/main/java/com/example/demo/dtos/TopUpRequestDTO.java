package com.example.demo.dtos;

import lombok.Data;

@Data
public class TopUpRequestDTO {
    Long balance;
    String userId;
    String id;
}
