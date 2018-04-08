package com.pole;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = "com.pole.core.mysql")
public class PoleApplication {
	public static void main(String[] args) {
		SpringApplication.run(PoleApplication.class, args);
	}
}
