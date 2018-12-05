package com.dapo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ZontaEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZontaEurekaServerApplication.class, args);
    }
}