package com.example.demo.repositories.tables.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "TYPE_ID")
    Long typeId;

    @Column(name = "NAME")
    String name;

    @Column(name = "IMAGE_ID")
    Long imageId;

    @Column(name = "CREATE_DATE")
    Date create_date;

    @Column(name = "CREATE_USER")
    String create_user;
}
