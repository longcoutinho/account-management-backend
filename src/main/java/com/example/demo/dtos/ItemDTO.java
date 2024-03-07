package com.example.demo.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemDTO {
    Long id;
    Long price;
    Long typeId;
    String name;
    List<MultipartFile> listImages;
    List<Long> listImageIds;
    int page;
    int pageSize;
}
