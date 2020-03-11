package com.anxinghei.sys;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@EnableTransactionManagement
public class HotelingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelingSystemApplication.class, args);
	}

}
