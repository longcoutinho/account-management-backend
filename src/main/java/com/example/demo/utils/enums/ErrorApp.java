package com.example.demo.utils.enums;

public enum ErrorApp {
    WRONG_LOGIN("Wrong username or password"),
    USERNAME_EXIST("Username is exist"),
    EXIST_ITEM_HAS_TYPE("Exist item has this type"),
    INVALID_ORDER("Invalid Order"),
    TOO_MANY_REQUEST("Too many request. Please try again later"),
    IGG_INVALID_ID("IGG ID invalid"),
    WRONG_OTP("Wrong OTP"),
    ACCESS_DENIED("Access denied"),
    INSUFFICIENT_BALANCE("Insufficient balance"),
    LOGIN_FAILED("Login failed"),
    INVALID_PAYMENT("Invalid payment"),
    ORDER_CARD_FAILED("Order card fail"),
    FILE_NOT_EXIST("File not exist");

    private final String description;

    ErrorApp(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
