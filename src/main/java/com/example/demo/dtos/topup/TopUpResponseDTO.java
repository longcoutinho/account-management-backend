package com.example.demo.dtos.topup;

import com.example.demo.repositories.tables.entities.TopUpEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TopUpResponseDTO {
    List<TopUpEntity> listTopUp;

    Long totalAmount;

    Long totalRequest;
}
