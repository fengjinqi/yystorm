package com.yystorm.oss;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.PutObjectResult;
import com.yystorm.execptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Component
public class Upload {
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.endpoint}")
    private String endpoint;

    public String upload(MultipartFile file,String name) {
        if (file == null) {
            throw new GuliException(400, "文件不能为空");
        }
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        // yuy76t5rew01.jpg
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        String datePath = new DateTime().toString("yyyy/MM/dd");
        fileName = name+"/"+datePath + "/" + uuid +prefix;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            ossClient.putObject("yystorm", fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            return fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ossClient.shutdown();
        }

    }
    public void deleteFile( String url) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject("yystorm",url);
    }

}
