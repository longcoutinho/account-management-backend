package com.example.demo.utils.exception;

import com.example.demo.utils.enums.ErrorApp;
import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    private ErrorApp errorApp;
    public CustomException(ErrorApp errorApp) {
        super(errorApp.getDescription());
        this.errorApp = errorApp;
    }
}
