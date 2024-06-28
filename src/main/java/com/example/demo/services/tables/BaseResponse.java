package com.example.demo.services.tables;

import lombok.Data;

import java.util.List;

@Data
public class BaseResponse {
    List<Object> listData;
    Long count;
}
