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

    @Autowired
    ImageServiceJPA imageServiceJPA;

    @Autowired
    StockAccountRepositoryJPA stockAccountRepositoryJPA;

    public ResponseItemDTO getItem(ItemDTO params) {
        List<ItemEntity> itemEntity = itemRepositoryJPA.getAllItem(PageRequest.of(params.getPage(), params.getPageSize()), params.getName(), params.getTypeId());
        ResponseItemDTO res = new ResponseItemDTO();
        res.setListData(convertFromListEntity(itemEntity));
        res.setCount(itemRepositoryJPA.countItem(params.getName(), params.getTypeId()));
        return res;
    }

    public ItemDTO getItemById(Long id) {
        ItemEntity itemEntity = itemRepositoryJPA.findById(id).get();
        return convertFromEntity(itemEntity);
    }

    public Object createNewItem(ItemDTO params) throws IOException {
        // save item
        ItemEntity newItem = new ItemEntity();
        newItem.setName(params.getName());
        newItem.setPrice(params.getPrice());
        newItem.setTypeId(params.getTypeId());
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
        itemDTO.setTypeId(entity.getTypeId());
        itemDTO.setListImageIds(imageServiceJPA.getImagesByItemId(entity.getId()));
        itemDTO.setAmount(stockAccountRepositoryJPA.findByItemId(entity.getId()));
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
//        if (level == 1) {
//            return itemRepositoryJPA.findByLv1TypeId(typeId);
//        }
//        else if (level == 2) {
//            return itemRepositoryJPA.findByLv2TypeId(typeId);
//        }
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
        if (params.getName() != null && !params.getName().equals(item.getName())) item.setName(params.getName());
        return 1L;
    }
}
