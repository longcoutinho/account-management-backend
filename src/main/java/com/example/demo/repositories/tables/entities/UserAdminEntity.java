package com.example.demo.repositories.tables.entities;

import com.example.demo.dtos.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ADMIN_USER")
public class UserAdminEntity implements Serializable {
    @Id
    @Column(name = "USERNAME")
    String username;

    @Column(name = "PASSWORD")
    String password;
}
