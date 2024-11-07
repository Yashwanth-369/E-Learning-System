package com.example.team.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for API
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/users/register",
                    "/api/users/login",
                    "/api/users/reset-password-request",
                    "/api/users/reset-password"
                ).permitAll() // Allow public access to these endpoints
                .anyRequest().authenticated() // Require authentication for all other endpoints
            )
            .httpBasic(withDefaults()); // Enable Basic Auth for stateless API

        http.logout(logout -> logout
            .logoutUrl("/api/logout")
            .permitAll()
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt for password encoding
    }
}

/* 
package com.example.team.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for API
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/users/register",
                    "/api/users/login",
                    "/api/users/reset-password-request",
                    "/api/users/reset-password"
                ).permitAll() // Allow public access to these endpoints
                .anyRequest().authenticated() // Require authentication for all other endpoints
            )
            .httpBasic(httpBasic -> {}); // Enable Basic Auth for stateless API without deprecation warning

        http.logout(logout -> logout
            .logoutUrl("/api/logout")
            .permitAll()
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt for password encoding
    }
}
*/