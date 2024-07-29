package com.example.demo.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountAllOrderDTO {
    @Column(name = "total_amount")
    Long total_amount;

//    @Column(name = "totalRevenue")
//    Long totalRevenue;
}
