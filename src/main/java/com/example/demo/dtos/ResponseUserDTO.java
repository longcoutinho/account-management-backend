package com.example.demo.dtos;

import com.example.demo.repositories.tables.entities.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUserDTO {
    public String email;
    public String id;
    public String fullName;
    public String phoneNumber;
    public String accessToken;
}
