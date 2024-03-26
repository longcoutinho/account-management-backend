package com.example.demo.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TopUpRequestDTO {
    Long amount;
    String username;
    Long id;
    Long method;
    String createTime;

    public TopUpRequestDTO(Long id) {
        this.id = id;
    }
}
