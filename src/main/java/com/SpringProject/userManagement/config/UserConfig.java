package com.SpringProject.userManagement.config;

// Deactivated conflicting InMemoryUserDetailsManager configuration bean to allow
// Spring Security to correctly autowire CustomUserDetailService globally.
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password("{noop}admin123")
                        .roles("USER")
                        .build()
        );
    }
}
*/
