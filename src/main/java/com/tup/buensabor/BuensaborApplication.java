package com.tup.buensabor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.tup.buensabor.mappers", "com.tup.buensabor"})
public class BuensaborApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuensaborApplication.class, args);
	}

}
