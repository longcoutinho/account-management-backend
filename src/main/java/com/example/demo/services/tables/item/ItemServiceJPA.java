package com.example.demo.services.tables.item;

import com.example.demo.dtos.ItemDTO;
import com.example.demo.repositories.tables.ItemRepositoryJPA;
import com.example.demo.repositories.tables.entities.ItemEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class ItemServiceJPA {
    @Autowired
    ItemRepositoryJPA itemRepositoryJPA;

    @Autowired
    ImageServiceJPA imageServiceJPA;

    public Object getItem(ItemDTO params) {
        List<ItemEntity> itemEntity = itemRepositoryJPA.getAllItem(params.getLv1Id(), params.getLv2Id(), params.getName());
        return convertFromListEntity(itemEntity);
    }

    public Object getItemById(Long id) {
        ItemEntity itemEntity = itemRepositoryJPA.findById(id).get();
        return convertFromEntity(itemEntity);
    }

    public Object createNewItem(ItemDTO params) throws IOException {
        // save item
        ItemEntity newItem = new ItemEntity();
        newItem.setName(params.getName());
        newItem.setPrice(params.getPrice());
        newItem.setLv1TypeId(params.getLv1Id());
        newItem.setLv2TypeId(params.getLv2Id());
        ItemEntity savedItem = itemRepositoryJPA.save(newItem);
        // save image
        imageServiceJPA.saveImage(params.getListImages(), savedItem.getId());
        return newItem;
    }

    private ItemDTO convertFromEntity(ItemEntity entity) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(entity.getName());
        itemDTO.setPrice(entity.getPrice());
        itemDTO.setId(entity.getId());
        itemDTO.setLv1Id(entity.getLv1TypeId());
        itemDTO.setLv2Id(entity.getLv2TypeId());
        itemDTO.setListImageIds(imageServiceJPA.getImagesByItemId(entity.getId()));
        return itemDTO;
    }

    private List<ItemDTO> convertFromListEntity(List<ItemEntity> list) {
        List<ItemDTO> result = new LinkedList<>();
        for(ItemEntity entity: list) {
            ItemDTO itemDTO = convertFromEntity(entity);
            result.add(itemDTO);
        }
        return result;
    }

    public List<ItemEntity> getItemByTypeIdAndLevel(Long typeId, Long level) {
        if (level == 1) {
            return itemRepositoryJPA.findByLv1TypeId(typeId);
        }
        else if (level == 2) {
            return itemRepositoryJPA.findByLv2TypeId(typeId);
        }
        return new LinkedList<>();
    }

    @Transactional
    public Object deleteItem(Long id) {
        itemRepositoryJPA.deleteById(id);
        return 1L;
    }

    public Object editItem(Long id, ItemDTO params) {
        ItemEntity item = itemRepositoryJPA.findById(id).get();
        if (params.getPrice() != null && !params.getPrice().equals(item.getPrice())) item.setPrice(params.getPrice());
        if (params.getLv1Id() != null && !params.getLv1Id().equals(item.getLv1TypeId())) item.setLv1TypeId(params.getLv1Id());
        if (params.getLv2Id() != null && !params.getLv2Id().equals(item.getLv2TypeId())) item.setLv2TypeId(params.getLv2Id());
        if (params.getName() != null && !params.getName().equals(item.getName())) item.setName(params.getName());
        return 1L;
    }
}
