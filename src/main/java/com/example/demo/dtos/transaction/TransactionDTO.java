package com.example.demo.dtos.transaction;

import lombok.Data;

@Data
public class TransactionDTO {
    Long type;
    Long gameId;
    Long cardId;
}
