package com.yystorm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;


@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
    //后台监控
    @Bean
    public ServletRegistrationBean statviewservlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //后台需要有人登陆，账号密码配置
        HashMap<String, String> initParameters = new HashMap<>();
        //登陆的key是固定的loginUsername  loginPassword
        initParameters.put("loginUsername","admin");
        //允许谁可以访问
        initParameters.put("allow","127.0.0.1");
        /**禁止谁能访问
         *initParameters.put("xxxx","xxxx");
         */
        initParameters.put("loginPassword","123456");
        bean.setInitParameters(initParameters);;//设置初始化参数
        return bean;
    }
    //filter
    @Bean
    public FilterRegistrationBean webStartFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        //可以过滤哪些请求
        HashMap<String, String> initParamters = new HashMap<>();
        initParamters.put("exclusion","*.js,*.css,/druid/*");
        bean.setInitParameters(initParamters);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}

