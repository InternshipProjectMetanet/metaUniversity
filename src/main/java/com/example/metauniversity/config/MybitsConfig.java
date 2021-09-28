package com.example.metauniversity.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.example.metauniversity.domain.Reply.mapper")
public class MybitsConfig {
	
	
}
