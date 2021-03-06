package com.yystorm.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //this.setFieldValByName("gmtCreate",new Date(),metaObject);
        //this.setFieldValByName("gmtModified",new Date(),metaObject);
        this.strictInsertFill(metaObject, "gmtCreate",LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
       // this.setFieldValByName("gmtModified",new Date(),metaObject);
        this.strictUpdateFill(metaObject, "gmtModified",LocalDateTime.class, LocalDateTime.now());
        // this.strictUpdateFill(metaObject, "gmt_modified", LocalDateTime.class, new Date()); // 起始版本 3.3.0(推荐使用)
       // this.fillStrategy(metaObject, "updateTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
        /* 上面选其一使用,下面的已过时(注意 strictUpdateFill 有多个方法,详细查看源码) */
        //this.setFieldValByName("operator", "Tom", metaObject);
        //this.setUpdateFieldValByName("operator", "Tom", metaObject);
    }
}