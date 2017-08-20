package com.example.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(value = "com.example.datasource.base")
@EnableTransactionManagement
@SpringBootApplication
public class MultiDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiDatasourceApplication.class, args);
	}
}
