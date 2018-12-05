package com.dapo.auth.config;


import com.dapo.auth.authentication.BasicAuthenticationProvider;
import com.dapo.auth.authentication.MultiAuthenticationManager;
import com.dapo.auth.authentication.MultiAuthenticationUserDetailService;
import com.dapo.auth.service.UserService;
import com.dapo.auth.service.impl.JpaUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AuthenticationManagerConfig {

    @Bean
    public UserService jpaUserService(){
        return new JpaUserService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new BasicAuthenticationProvider(new JpaUserService());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new MultiAuthenticationManager()
                .addProvider(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService(AuthenticationManager authenticationManager) {
        return new MultiAuthenticationUserDetailService(authenticationManager);
    }
}
