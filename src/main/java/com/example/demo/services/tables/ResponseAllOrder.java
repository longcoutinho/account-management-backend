package com.example.demo.services.tables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ResponseAllOrder extends BaseResponse{
    Long totalRevenue;
}
