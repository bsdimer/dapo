package com.dapo.auth;

import com.dapo.auth.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class DapoAuthorizationService {

	public static void main(String[] args) {
		SpringApplication.run(DapoAuthorizationService.class, args);
	}
}
