package com.example.gamemanagement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class GameManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(GameManagementApplication.class, args);
		log.info("Game Management Application started.");
	}

}
