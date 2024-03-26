package com.example.demo.services.tables.item;

import com.example.demo.repositories.tables.ItemRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceJPA {
    @Autowired
    ItemRepositoryJPA itemRepositoryJPA;

    public Object getItemByCardId(Long id) {
        return itemRepositoryJPA.findByCardId(id);
    }
}
