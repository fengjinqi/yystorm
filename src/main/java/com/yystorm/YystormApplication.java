package com.yystorm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication

public class YystormApplication {
    public static void main(String[] args) {
        SpringApplication.run(YystormApplication.class,args);
    }

}
