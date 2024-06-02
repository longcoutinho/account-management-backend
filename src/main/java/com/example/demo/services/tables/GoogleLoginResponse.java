package com.example.demo.services.tables;

import lombok.Data;

@Data
public class GoogleLoginResponse {
    private String id;
    private String email;
    private String verified_email;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String locale;
}
