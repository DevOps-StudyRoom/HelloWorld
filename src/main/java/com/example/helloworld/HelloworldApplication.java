package com.example.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HelloworldApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World hanium";
	}

    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }

}
