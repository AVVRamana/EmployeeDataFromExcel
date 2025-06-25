package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.client.EmployeeClient;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public EmployeeClient getEmployeeClient() {
		return new EmployeeClient();
	}

	@Override
	public void run(String... args) throws Exception {
		getEmployeeClient().execute();
		
	}
	
	

}
