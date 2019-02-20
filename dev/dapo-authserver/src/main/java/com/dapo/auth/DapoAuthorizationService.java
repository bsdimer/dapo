package com.dapo.auth;

import com.dapo.auth.common.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(AppProperties.class)
public class DapoAuthorizationService {

	public static void main(String[] args) {
		SpringApplication.run(DapoAuthorizationService.class, args);
	}
}
