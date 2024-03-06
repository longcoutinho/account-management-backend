package com.example.demo.dtos;

import lombok.Data;

@Data
public class TopUpRequestDTO {
    Long amount;
    String username;
    Long id;
    Long method;
    String createTime;
}
