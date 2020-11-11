package com.yystorm.controller;

import cn.hutool.json.JSONObject;
import com.yystorm.handler.JwtToken;
import com.yystorm.oss.Upload;
import com.yystorm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("articles")
    @CrossOrigin
    public JSONObject uploads(@RequestParam(value = "editormd-image-file") MultipartFile file) {
        String upload = this.upload.upload(file,"article");
        JSONObject jsonObject = new JSONObject();
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("url","https://yystorm.oss-cn-beijing.aliyuncs.com/"+upload);
        hashMap.put("message","成功");
        hashMap.put("success",1);
        jsonObject.putAll(hashMap);
        return jsonObject;
    }

    @JwtToken
    @PostMapping("/delete")
    public Result del(@RequestBody  String file) throws IOException {

        upload.deleteFile(file);
        return Result.ok();
    }
}
