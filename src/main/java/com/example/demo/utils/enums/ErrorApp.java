package com.example.demo.utils.enums;

public enum ErrorApp {
    WRONG_LOGIN("Wrong username or password"),
    USERNAME_EXIST("Username is exist"),
    EXIST_ITEM_HAS_TYPE("Exist item has this type"),
    INVALID_ORDER("Invalid Order");
    private final String description;

    ErrorApp(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
