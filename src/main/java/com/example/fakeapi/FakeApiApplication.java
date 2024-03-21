package com.example.fakeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FakeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeApiApplication.class, args);
	}

}
