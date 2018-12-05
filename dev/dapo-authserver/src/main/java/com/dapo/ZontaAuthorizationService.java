package com.dapo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Created by Dimitar Dimitrov
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
public class ZontaAuthorizationService {

    public static void main(String[] args) {
        SpringApplication.run(ZontaAuthorizationService.class, args);
    }

}
