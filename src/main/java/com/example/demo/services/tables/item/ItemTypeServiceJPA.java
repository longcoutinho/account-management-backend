package com.example.demo.services.tables.item;

import com.example.demo.dtos.ItemTypeDTO;
import com.example.demo.repositories.tables.ItemTypeRepositoryJPA;
import com.example.demo.repositories.tables.entities.ItemEntity;
import com.example.demo.repositories.tables.entities.ItemTypeEntity;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTypeServiceJPA {
    @Autowired
    ItemTypeRepositoryJPA itemTypeRepositoryJPA;

    @Autowired
    ItemServiceJPA itemServiceJPA;

    public Object createNewType(ItemTypeDTO params) {
        ItemTypeEntity entity = new ItemTypeEntity();
        entity.setCode(params.getCode());
        entity.setName(params.getName());
        entity.setCreateUser(null);
        entity.setCreateDate(null);
        entity.setLevel(params.getLevel());
        entity.setParentId(params.getParentId());
        itemTypeRepositoryJPA.save(entity);
        return entity;
    }

    public Object getItemType(ItemTypeDTO params) {
        if (params.getId() != null) {
            return itemTypeRepositoryJPA.findById(params.getId());
        }
        return itemTypeRepositoryJPA.findByLevelAndParentIdOrderByName(params.getLevel(), params.getParentId());
    }

    public Object editItemType(Long id, ItemTypeDTO params) {
        ItemTypeEntity itemTypeEntity = itemTypeRepositoryJPA.findById(id).get();
        if (params.getName() != null) {
            itemTypeEntity.setName(params.getName());
        }
        if (params.getCode() != null) {
            itemTypeEntity.setCode(params.getCode());
        }
        itemTypeRepositoryJPA.save(itemTypeEntity);
        return itemTypeEntity;
    }

    @Transactional
    public Object deleteItemType(Long id) {
        ItemTypeEntity itemType = itemTypeRepositoryJPA.findById(id).get();
        List<ItemEntity> listItem = itemServiceJPA.getItemByTypeIdAndLevel(id, itemType.getLevel());
        if (!listItem.isEmpty()) throw new CustomException(ErrorApp.EXIST_ITEM_HAS_TYPE);
        itemTypeRepositoryJPA.delete(itemType);
        //remove child
        if (itemType.getLevel() == 1) {
            itemTypeRepositoryJPA.deleteByParentId(id);
        }
        return 1L;
    }
}
