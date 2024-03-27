package com.example.demo.dtos.saleorder;

import com.example.demo.dtos.saleorder.SaleOrderResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class SaleOrderResponseSummaryDTO {
    List<SaleOrderResponseDTO> listSaleOrder;

    Long totalAmount;

    Long totalRequest;
}
