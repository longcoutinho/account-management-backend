package com.example.demo.services.tables.item;

import com.example.demo.dtos.SizeDTO;
import com.example.demo.repositories.tables.SizeRepositoryJPA;
import com.example.demo.repositories.tables.entities.SizeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceJPA {
    @Autowired
    SizeRepositoryJPA sizeRepositoryJPA;

    public Object getSize(SizeDTO params) {
        return sizeRepositoryJPA.getAllSize();
    }

    public Object createNewSize(SizeDTO params) {
        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setCode(params.getCode());
        sizeEntity.setName(params.getName());
        sizeRepositoryJPA.save(sizeEntity);
        return sizeEntity;
    }
}
