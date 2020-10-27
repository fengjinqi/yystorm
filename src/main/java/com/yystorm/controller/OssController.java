package com.yystorm.controller;

import com.yystorm.handler.JwtToken;
import com.yystorm.oss.Upload;
import com.yystorm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/oss")
public class OssController {
    @Autowired
    private Upload upload;
    @JwtToken
    @PostMapping("article")
    public Result upload(@RequestParam(value = "file") MultipartFile file,@RequestParam("name") String name) {
        String upload = this.upload.upload(file,name);
        return Result.ok().data("url", upload);
    }
    @JwtToken
    @PostMapping("/delete")
    public Result del(@RequestBody  String file) {
        upload.deleteFile(file);
        return Result.ok();
    }
}
