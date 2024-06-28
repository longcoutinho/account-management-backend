package com.example.demo.controllers.card;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RequestAllOrder {
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    Date fromDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    Date toDate;
    Long page;
    Long pageSize;
    String status;
    String username;
    String transId;
}
