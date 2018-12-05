package com.dapo.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by dimer on 21.11.17.
 */

@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
public class DapoWebSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(DapoWebSiteApplication.class, args);
    }
}
