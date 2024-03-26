package com.example.demo.services.tables.item;

import com.example.demo.dtos.ItemDTO;
import com.example.demo.dtos.item.ResponseItemDTO;
import com.example.demo.repositories.tables.ItemRepositoryJPA;
import com.example.demo.repositories.tables.StockAccountRepositoryJPA;
import com.example.demo.repositories.tables.entities.ItemEntity;
import com.example.demo.repositories.tables.entities.StockAccountEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class ItemServiceJPA {
    @Autowired
    ItemRepositoryJPA itemRepositoryJPA;

    public Object getItemByCardId(Long id) {
        return itemRepositoryJPA.findByCardId(id);
    }
}
