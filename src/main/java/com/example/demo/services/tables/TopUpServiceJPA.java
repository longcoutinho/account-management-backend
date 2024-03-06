package com.example.demo.services.tables;

import com.example.demo.dtos.GetAllTopUpDTO;
import com.example.demo.dtos.TopUpRequestDTO;
import com.example.demo.repositories.tables.TopUpRepositoryJPA;
import com.example.demo.repositories.tables.entities.TopUpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TopUpServiceJPA {
    @Autowired
    TopUpRepositoryJPA topUpRepositoryJPA;

    @Autowired
    UserServiceJPA userServiceJPA;

    public Object createRequest(TopUpRequestDTO params) {
        TopUpEntity topUpEntity = new TopUpEntity();
        topUpEntity.setAmount(params.getAmount());
        topUpEntity.setStatus(0L);
        topUpEntity.setUsername(params.getUsername());
        topUpEntity.setMethod(params.getMethod());
        topUpEntity.setCreateDate(new Date(System.currentTimeMillis()));
        topUpRepositoryJPA.save(topUpEntity);
        return topUpEntity;
    }

    public Object confirm(TopUpRequestDTO params) {
        TopUpEntity topUpEntity = topUpRepositoryJPA.findById(params.getId()).get();
        topUpEntity.setStatus(1L); //success;
        TopUpRequestDTO topUpRequestDTO = new TopUpRequestDTO();
        topUpRequestDTO.setAmount(topUpEntity.getAmount());
        topUpRequestDTO.setUsername(topUpEntity.getUsername());
        userServiceJPA.addBalance(topUpRequestDTO);
        return 1L;
    }

    public Object getAll(GetAllTopUpDTO params) {
        return topUpRepositoryJPA.getAll(params.getStatus(), params.getUsername());
    }
}
