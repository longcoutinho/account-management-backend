package com.example.demo.services.topupgame;

import com.example.demo.dtos.UserDTO;
import com.example.demo.repositories.tables.TopUpItemRepositoryJPA;
import com.example.demo.repositories.tables.entities.TopUpItemEntity;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopUpItemServiceJPA {
    @Autowired
    TopUpItemRepositoryJPA topUpItemRepositoryJPA;

    public Object getAll(String gameId) {
        return topUpItemRepositoryJPA.getAll(gameId);
    }
}
