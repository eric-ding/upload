package com.dc.controller;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

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
//    public String uploadFile(@RequestPart("file") MultipartFile file,
//                             @RequestPart("files") MultipartFile[] files ) throws IOException {
//       if(!file.isEmpty()){
//           String fileName = file.getOriginalFilename();
//           String filePath = path + fileName;
//           file.transferTo(new File(filePath));
//       }
//       if(files.length > 0){
//           for (MultipartFile music_file : files){
//               if(!music_file.isEmpty()){
//                   String fileName = music_file.getOriginalFilename();
//                   String filePath = path + fileName;
//                   music_file.transferTo(new File(filePath));
//               }
//           }
//       }
//        return "redirect:/";
//    }

    public String uploadFile(@RequestParam("file-path") String filePath,
                             @RequestParam("file-path3") String filePath3) throws IOException {
        File file = new File(filePath);
        String fileName = file.getName();
        String contentType = "audio/mpeg";
        byte[] content = FileUtils.readFileToByteArray(file); // 使用 Apache Commons IO 读取文件内容
        MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, contentType, content);
        if (!multipartFile.isEmpty()) {
            // 处理文件上传
            File dest = new File(path, file.getName()); // 设置文件上传的目标路径和文件名
            multipartFile.transferTo(dest);
        }

        File file3 = new File(filePath3);
        String fileName3 = file3.getName();
        String contentType3 = "audio/mpeg";
        byte[] content3 = FileUtils.readFileToByteArray(file3); // 使用 Apache Commons IO 读取文件内容
        MultipartFile multipartFile3 = new MockMultipartFile(fileName3, fileName3, contentType3, content3);
        if (!multipartFile3.isEmpty()) {
            // 处理文件上传
            File dest3 = new File(path, file3.getName()); // 设置文件上传的目标路径和文件名
            multipartFile3.transferTo(dest3);
        }
        return "redirect:/";
    }
}
