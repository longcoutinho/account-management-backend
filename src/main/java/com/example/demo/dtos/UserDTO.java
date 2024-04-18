package com.example.demo.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Connection;
import java.sql.ConnectionBuilder;
import java.sql.SQLException;
import java.sql.ShardingKey;

@Data
public class UserDTO {
    @Size(min = 6, message = "{validation.name.size.too_short}")
    @Size(max = 12, message = "{validation.name.size.too_long}")
    @NotNull
    String username;

    @Size(min = 6, message = "{validation.name.size.too_short}")
    @Size(max = 24, message = "{validation.name.size.too_long}")
    @NotNull
    String password;

    String id;

    Long type;

    String email;
}
