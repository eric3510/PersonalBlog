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
@MapperScan(basePackages = "com.personalblog.core")
public class PersonalBlogApplication{
    private static final String TYPE_ALIASES_PACKAGE = "com.personalblog.core.mysql";
    private static final String MAPPER_LOCATION = "classpath:/mybatis/*.xml";

//    @Bean
//    @Autowired
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
//        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
//        return sqlSessionFactoryBean.getObject();
//    }

    public static void main(String[] args){
        SpringApplication.run(PersonalBlogApplication.class, args);
    }
}

