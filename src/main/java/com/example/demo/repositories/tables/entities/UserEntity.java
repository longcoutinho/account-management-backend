package com.example.demo.repositories.tables.entities;

import com.example.demo.dtos.ResponseUserDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.utils.constants.Constants;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
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

    @Column(name = "BALANCE")
    Long balance;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "ROLE")
    String role;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "LOGIN_METHOD")
    String loginMethod;

    public enum Role {
        USER("USER"),
        ADMIN("ADMIN");

        public String value;
        Role(String value) {
            this.value = value;
        }
    }

    public UserEntity(UserDTO user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.userId = String.valueOf(UUID.randomUUID());
        this.balance = 0L;
        this.createDate = new Date(System.currentTimeMillis());
        this.role = user.getRole();
        this.email = user.getEmail();
        this.loginMethod = String.valueOf(Constants.LoginMethod.DIRECT);
    }

    public ResponseUserDTO convertFromEntity() {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        responseUserDTO.setId(userId);
        responseUserDTO.setBalance(balance);
        responseUserDTO.setCreateDate(createDate);
        responseUserDTO.setUsername(username);
        responseUserDTO.setEmail(email);
        return responseUserDTO;
    }
}
