package com.example.demo.repositories.tables.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ITEM")
public class ItemEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "PRICE")
    Long price;

    @Column(name = "LV1_TYPE_ID")
    Long lv1TypeId;

    @Column(name = "LV2_TYPE_ID")
    Long lv2TypeId;

    @Column(name = "NAME")
    String name;
}
