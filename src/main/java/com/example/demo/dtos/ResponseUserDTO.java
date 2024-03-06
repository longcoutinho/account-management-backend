package com.example.demo.dtos;

import com.example.demo.repositories.tables.entities.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ResponseUserDTO {
    public Date createDate;
    public String id;
    public String accessToken;
    public Long balance;
}
