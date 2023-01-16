package com.test.thymeleafcontroller.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //Let url = "/customers" accessible for everyone
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((auth)->
                        auth.requestMatchers("/customers").permitAll().anyRequest().authenticated())
                .formLogin()
                .and()
                .csrf();
        return httpSecurity.build();
    }

}
