package com.example.demo.services.tables;

import com.example.demo.dtos.FileDTO;
import com.example.demo.repositories.tables.FileRepositoryJPA;
import com.example.demo.repositories.tables.entities.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceJPA {
    @Autowired
    FileRepositoryJPA fileRepositoryJPA;

    public FileEntity saveFile(MultipartFile file) throws IOException {
        String userHomeFolder = System.getProperty("user.home") + File.separator + "project_file";
        File textFile = new File(userHomeFolder, file.getOriginalFilename());
        FileOutputStream outputStream = new FileOutputStream(textFile);
        outputStream.write(file.getBytes());
        FileEntity fileEntity = new FileEntity();
        fileEntity.setPath(textFile.getPath());
        fileEntity.setName(file.getName());
        return fileRepositoryJPA.save(fileEntity);
    }

    public byte[] getByteFileById(Long id) throws IOException {
        FileEntity file = fileRepositoryJPA.findById(id).get();
        byte[] array = Files.readAllBytes(Paths.get(file.getPath()));
        return array;
    }
}
