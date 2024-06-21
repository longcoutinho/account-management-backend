package com.example.demo.services.tables.item;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardInfo {
    String code;
    String serial;
    String vendor;
    int value;
    String expiry;
}
