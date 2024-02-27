package com.example.demo.services.tables.item;

import com.example.demo.repositories.tables.ImageRepositoryJPA;
import com.example.demo.repositories.tables.entities.FileEntity;
import com.example.demo.repositories.tables.entities.ItemImageEntity;
import com.example.demo.services.tables.FileServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class ImageServiceJPA {
    @Autowired
    FileServiceJPA fileServiceJPA;

    @Autowired
    ImageRepositoryJPA imageRepositoryJPA;

    private byte[] getImageByFileId(Long id) throws IOException {
        return fileServiceJPA.getByteFileById(id);
    }

    public void saveImage(List<MultipartFile> listImages, Long imageId) throws IOException {
        for(MultipartFile image: listImages) {
            // save file to server
            FileEntity savedFile = fileServiceJPA.saveFile(image);
            ItemImageEntity itemImageEntity = new ItemImageEntity();
            itemImageEntity.setItemId(imageId);
            itemImageEntity.setFileId(savedFile.getId());
            imageRepositoryJPA.save(itemImageEntity);
        }
    }

    public byte[] getImageById(Long id) throws IOException {
        ItemImageEntity itemImageEntity = imageRepositoryJPA.findById(id).get();
        return getImageByFileId(itemImageEntity.getFileId());
    }

    public List<Long> getImagesByItemId(Long itemId) {
        List<ItemImageEntity> list = imageRepositoryJPA.findByItemId(itemId);
        List<Long> result = new LinkedList<>();
        for(ItemImageEntity item: list) {
            Long imageId = item.getId();
            result.add(imageId);
        }
        return result;
    }
}
