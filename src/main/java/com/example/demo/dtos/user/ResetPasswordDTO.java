package com.example.demo.dtos.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordDTO {
    String username;

    @Size(min = 6, message = "{validation.name.size.too_short}")
    @Size(max = 24, message = "{validation.name.size.too_long}")
    @NotNull
    String password;
}
