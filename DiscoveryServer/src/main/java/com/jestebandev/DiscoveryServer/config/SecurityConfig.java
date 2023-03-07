package com.jestebandev.DiscoveryServer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.eureka.username}")
    private String username;
    @Value("${app.eureka.password}")
    private String password;

    //this is how works in the new Spring Security 5.7.0
    @Bean
    protected InMemoryUserDetailsManager configAuthentication() {
        List<UserDetails> users = new ArrayList<>();
        List<GrantedAuthority> adminAuthority = new ArrayList<>();
        adminAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails admin = new User(username, passwordEncoder().encode(password), adminAuthority);
        users.add(admin);
        return new InMemoryUserDetailsManager(users);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
        return http.build();
    }
}
