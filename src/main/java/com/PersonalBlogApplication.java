package com;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan(basePackages = "com.personalblog.core.mysql")
public class PersonalBlogApplication{
    private static final String TYPE_ALIASES_PACKAGE = "com.personalblog.core.mysql";
    private static final String MAPPER_LOCATION = "classpath:/mybatis/*.xml";
    public static void main(String[] args){
        SpringApplication.run(PersonalBlogApplication.class, args);
    }
}

