package com.example.demo.services.tables;

import com.example.demo.dtos.StockAccountDTO;
import com.example.demo.repositories.tables.GameRepositoryJPA;
import com.example.demo.repositories.tables.entities.GameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class GameServiceJPA {
    @Autowired
    GameRepositoryJPA gameRepositoryJPA;

    public Object getAll() {
        return gameRepositoryJPA.getAll();
    }
}
