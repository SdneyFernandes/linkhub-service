package br.com.sdney.linkhub_service;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCaching
@SpringBootApplication
public class LinkhubServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkhubServiceApplication.class, args);
	}

}
