package org.tech.bootcampp.security;
 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityBeansConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // 🔥 Disable Spring’s default in-memory user
        return username -> {
            throw new RuntimeException("UserDetailsService disabled, JWT only");
        };
    }
}

