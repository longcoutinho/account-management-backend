package com.example.demo.dtos;

import com.example.demo.dtos.SaleOrderResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class SaleOrderResponseSummaryDTO {
    List<SaleOrderResponseDTO> listSaleOrder;

    Long totalAmount;

    Long totalRequest;
}
