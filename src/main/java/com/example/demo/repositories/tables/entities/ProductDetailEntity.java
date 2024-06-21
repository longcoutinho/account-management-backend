package com.example.demo.repositories.tables.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_DETAIL")
public class ProductDetailEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "PRODUCT_ID")
    Long productId;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "IMAGE_LIST")
    String imageList;
}
