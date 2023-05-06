package com.dc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
public class HelloController {

    @Value("${file.upload.path}")
    private String path;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String handle01(){
        return "hello, Srping Boot 2";
    }


    @PostMapping("/upload")
    public String uploadFile(@RequestPart("file") MultipartFile file,
                             @RequestPart("files") MultipartFile[] files ) throws IOException {
       if(!file.isEmpty()){
           String fileName = file.getOriginalFilename();
           String filePath = path + fileName;
           file.transferTo(new File(filePath));
       }
       if(files.length > 0){
           for (MultipartFile music_file : files){
               if(!music_file.isEmpty()){
                   String fileName = music_file.getOriginalFilename();
                   String filePath = path + fileName;
                   music_file.transferTo(new File(filePath));
               }
           }
       }


        return "redirect:/";
    }

}
