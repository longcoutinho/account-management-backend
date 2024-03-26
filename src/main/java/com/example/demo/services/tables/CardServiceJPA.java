package com.example.demo.services.tables;

import com.example.demo.repositories.tables.CardRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceJPA {
    @Autowired
    CardRepositoryJPA cardRepositoryJPA;


    public Object getAll() {
        return cardRepositoryJPA.getAll();
    }
}
