package com.dc.controller;

import org.springframework.http.MediaType;
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

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> upload(@RequestPart("musics") MultipartFile[] musics) throws IOException {
        List<String> fileNames = new ArrayList<>();
        if (musics.length > 0) {
            for (MultipartFile music : musics) {
                if (!music.isEmpty()) {
                    String musicName = music.getOriginalFilename();
                    music.transferTo(new File(UPLOAD_DIR + File.separator + musicName));
                    fileNames.add(musicName);
                }
            }
        }
        return fileNames;
    }
}
