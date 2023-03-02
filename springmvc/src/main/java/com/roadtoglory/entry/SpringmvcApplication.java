package com.roadtoglory.entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringmvcApplication {

	public static void main(String[] args) {
		System.out.println("inside the spring mvc boot");
		SpringApplication.run(SpringmvcApplication.class, args);
	}

}
