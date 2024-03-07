package com.example.demo.dtos.saleorder;

import com.example.demo.dtos.SaleOrderResponseDTO;
import com.example.demo.repositories.tables.entities.SaleOrderEntity;
import lombok.Data;

import java.util.List;

@Data
public class ResponseSaleOrderDTO {
    List<SaleOrderResponseDTO> listSaleOrder;

    Long totalAmount;

    Long totalRequest;
}
