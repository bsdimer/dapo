package com.dapo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DapoEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DapoEurekaServerApplication.class, args);
    }
}