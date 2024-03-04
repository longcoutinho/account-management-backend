package com.example.demo.dtos;

import lombok.Data;

@Data
public class TopUpRequestDTO {
    Long amount;
    String userId;
    String id;
    Long method;
    String createTime;
}
