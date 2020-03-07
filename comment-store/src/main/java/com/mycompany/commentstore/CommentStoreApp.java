package com.mycompany.commentstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EntityScan(basePackages= { "com.mycompany" })
public class CommentStoreApp {

	public static void main(String[] args) {
		SpringApplication.run(CommentStoreApp.class, args);
	}
	
	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}
}
