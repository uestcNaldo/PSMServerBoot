package com.uestc.naldo;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("com.uestc.naldo.dao")
public class PSMServerBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PSMServerBootApplication.class, args);
	}



}
