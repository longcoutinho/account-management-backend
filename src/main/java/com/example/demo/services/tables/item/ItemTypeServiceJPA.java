package com.example.demo.services.tables.item;

import com.example.demo.dtos.ItemTypeDTO;
import com.example.demo.repositories.tables.ItemTypeRepositoryJPA;
import com.example.demo.repositories.tables.entities.ItemTypeEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeServiceJPA {
    @Autowired
    ItemTypeRepositoryJPA itemTypeRepositoryJPA;

    @Autowired
    CardItemServiceJPA cardItemServiceJPA;

    public Object createNewType(ItemTypeDTO params) {
        ItemTypeEntity entity = new ItemTypeEntity();
        entity.setName(params.getName());
        entity.setCreateUser(null);
        entity.setCreateDate(null);
        itemTypeRepositoryJPA.save(entity);
        return entity;
    }

    public Object getItemType(ItemTypeDTO params) {
        return itemTypeRepositoryJPA.findAll();
    }

    public Object editItemType(Long id, ItemTypeDTO params) {
        ItemTypeEntity itemTypeEntity = itemTypeRepositoryJPA.findById(id).get();
        if (params.getName() != null) {
            itemTypeEntity.setName(params.getName());
        }
        itemTypeRepositoryJPA.save(itemTypeEntity);
        return itemTypeEntity;
    }

    @Transactional
    public Object deleteItemType(Long id) {
//        ItemTypeEntity itemType = itemTypeRepositoryJPA.findById(id).get();
//        if (!listItem.isEmpty()) throw new CustomException(ErrorApp.EXIST_ITEM_HAS_TYPE);
//        itemTypeRepositoryJPA.delete(itemType);
        return 1L;
    }
}
