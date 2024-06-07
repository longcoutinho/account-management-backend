package com.example.demo.services.tables.item;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardInfo {
    String code;
    String serial;
    String vendor;
    int value;
    Date expiry;
}
