package com.example.demo.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemDTO {
    Long id;
    Long price;
    Long lv1Id;
    Long lv2Id;
    String name;
    List<MultipartFile> listImages;
    List<Long> listImageIds;
}
