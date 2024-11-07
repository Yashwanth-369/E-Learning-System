package com.example.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.team")
public class TeamApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamApplication.class, args);

	}
}
