package com.example.demo.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileDTO {
    MultipartFile fileContent;
}
