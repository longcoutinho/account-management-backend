package com.example.demo.repositories.tables.entities;

import com.example.demo.dtos.ResponseUserDTO;
import com.example.demo.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class UserEntity implements Serializable {
    @Id
    @Column(name = "USER_ID")
    String userId;

    @Column(name = "USERNAME")
    String username;

    @Column(name = "PASSWORD")
    String password;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "PHONE_NUMBER")
    String phoneNumber;

    @Column(name = "FULL_NAME")
    String fullName;

    public UserEntity(UserDTO user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.fullName = user.getFullName();
        this.phoneNumber = user.getPhoneNumber();
        this.userId = String.valueOf(UUID.randomUUID());
    }

    public ResponseUserDTO convertFromEntity() {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        responseUserDTO.setEmail(email);
        responseUserDTO.setId(userId);
        responseUserDTO.setFullName(fullName);
        responseUserDTO.setPhoneNumber(phoneNumber);
        return responseUserDTO;
    }
}
