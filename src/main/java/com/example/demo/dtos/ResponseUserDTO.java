package com.example.demo.dtos;

import com.example.demo.repositories.tables.entities.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUserDTO {
    public String email;
    public String userId;
    public String fullName;
    public String phoneNumber;

    public ResponseUserDTO convertFromEntity(UserEntity user) {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        responseUserDTO.setEmail(user.getEmail());
        responseUserDTO.setUserId(user.getUserId());
        responseUserDTO.setFullName(user.getFullName());
        responseUserDTO.setPhoneNumber(user.getPhoneNumber());
        return responseUserDTO;
    }
}
