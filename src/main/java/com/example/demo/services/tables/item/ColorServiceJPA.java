package com.example.demo.services.tables.item;

import com.example.demo.dtos.SizeDTO;
import com.example.demo.repositories.tables.ColorRepositoryJPA;
import com.example.demo.repositories.tables.entities.ColorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceJPA {
    @Autowired
    ColorRepositoryJPA colorRepositoryJPA;

    public Object getColor(SizeDTO params) {
        return colorRepositoryJPA.getAllColor();
    }

    public Object createNewColor(SizeDTO params) {
        return colorRepositoryJPA.save(new ColorEntity(params.getCode(), params.getName()));
    }
}
