package com.example.demo.dtos;

import lombok.Data;

@Data
public class ItemTypeDTO {
    Long id;
    String code;
    String name;
    Long level;
    Long parentId;
}
