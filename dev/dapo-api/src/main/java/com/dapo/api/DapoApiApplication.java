package com.dapo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DapoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DapoApiApplication.class, args);
	}
}
