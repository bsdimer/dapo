package com.dapo.auth.config;


import com.dapo.auth.authentication.BasicAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;

@Configuration
public class AuthenticationManagerConfig {

    @Bean
    public AuthenticationProvider authenticationProvider(AuthenticationManager authenticationManager) {
        return new BasicAuthenticationProvider(authenticationManager);
    }

}
