package com.example.demo.dtos.item;

import com.example.demo.dtos.ItemDTO;
import lombok.Data;

import java.util.List;

@Data
public class ResponseItemDTO {
    List<ItemDTO> listData;
    Long count;
}
