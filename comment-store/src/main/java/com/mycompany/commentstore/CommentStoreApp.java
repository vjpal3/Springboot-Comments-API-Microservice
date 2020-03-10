package com.mycompany.commentstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

@ComponentScan(
		basePackages = {
				"com.mycompany",
				"com.mycompany.commentsapi"
		}
)

@EntityScan(basePackages= { "com.mycompany" })
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class CommentStoreApp {

	public static void main(String[] args) {
		SpringApplication.run(CommentStoreApp.class, args);
	}
	
	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}
}
