package com.dc;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadModel {
//    private String fileName;
//    private MultipartFile head;
//    private MultipartFile tail;
    private MultipartFile[] musics;
}
