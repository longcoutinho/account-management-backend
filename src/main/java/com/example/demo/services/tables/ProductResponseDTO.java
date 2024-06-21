package com.example.demo.services.tables;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDTO {
    String name;
    Long id;
    String imagePath;
    Long typeId;
}
