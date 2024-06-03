package com.example.demo.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    @NotNull
    String username;

    @NotNull
    String password;

    String id;

    String role;

    String loginMethod;

    String email;

    String accessToken;
}
