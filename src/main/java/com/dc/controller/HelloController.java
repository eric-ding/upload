package com.dc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    private static final String UPLOAD_DIR = "/Users/dingchao/test";
//    private static final String UPLOAD_DIR = "/home/dc/test";

    @PostMapping(value = "/upload")
    public List<String> upload(@RequestPart("musics") MultipartFile[] musics) throws IOException {
//        List<String> fileNames = new ArrayList<>();
        if (musics.length > 0) {
            for (MultipartFile music : musics) {
                if (!music.isEmpty()) {
                    String musicName = music.getOriginalFilename();
                    music.transferTo(new File(UPLOAD_DIR + File.separator + musicName));
//                    fileNames.add(musicName);
                }
            }
        }
        List<String> fileNames = getFileNames(UPLOAD_DIR);

        return fileNames;
    }

    public static List<String> getFileNames(String folderPath) {
        List<String> fileNames = new ArrayList<>();
        fileNames.add("请选择");
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }
        return fileNames;
    }
}
