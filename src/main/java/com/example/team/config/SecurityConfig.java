package com.example.team.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Enables method-level security
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for stateless APIs
            .csrf(csrf -> csrf.disable())

            // Allow requests from the Angular frontend (CORS configuration)
            .cors(Customizer.withDefaults())

            // Set session management to stateless
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Define endpoint authorization rules
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers(
                    "/api/users/register",
                    "/api/users/login",
                    "/api/users/logout",
                    "/api/users/reset-password-request",
                    "/api/users/reset-password",
                    "/api/users/verify-otp",
                    "/api/courses", // GET all courses
                    "/api/courses/{id}", // GET course by ID
                    "/api/courses/{id}/enroll",
                    "/api/durations",
                    "/api/durations/type",
                    "/api/durations/total-hours",
                    "/api/durations/description",
                    "/api/fees",
                    "/api/permissions",
                    "/api/roles",
                    "/api/schedules",
                    "/api/feedback/{userEmail}"
                ).permitAll()


                //.requestMatchers("/api/feedback/**").permitAll()

                // User-specific endpoints
                // .requestMatchers("/user/**").hasRole("student")

                // Admin-specific endpoints
                // .requestMatchers("/admin/**").hasRole("ADMIN")


                .anyRequest().permitAll()
            )

            // Enable HTTP Basic Authentication
            .httpBasic(Customizer.withDefaults())

            // Configure logout functionality
            .logout(logout -> logout
                .logoutUrl("/api/users/logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt for secure password encoding
    }
}











/* package com.example.team.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Enables method-level security
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for stateless APIs
            .csrf(csrf -> csrf.disable())

            // Set session management to stateless
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Define endpoint authorization rules
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers(
                    "/api/users/register",
                    "/api/users/login",
                    "/api/users/logout",
                    "/api/users/reset-password-request",
                    "/api/users/reset-password",
                    "/api/users/verify-otp",
                    "/api/courses", // GET all courses
                    "/api/courses/{id}", // GET course by ID
                    "/api/courses/{id}/enroll", 
                    "/api/durations",
                    "/api/durations/type",
                    "/api/durations/total-hours",
                    "/api/durations/description",
                    "/api/fees",
                    "/api/permissions",
                    "/api/roles",
                    "/api/schedules"
                ).permitAll()

                .requestMatchers("/admin/**").hasRole("ADMIN") // Automatically appends "ROLE_"
                .requestMatchers("/user/**").hasRole("USER")

                // User-specific endpoints
                .requestMatchers("/api/courses/{id}/enroll").hasRole("USER") // USER role can enroll

                // Admin-specific endpoints
                .requestMatchers(
                    "/api/courses/add",
                    "/api/courses/{id}/availability",
                    "/api/durations/add",
                    "/api/durations/{id}",
                    "/api/durations/{id}/availability",
                    "/api/durations/{id}/delete", // DELETE duration
                    "/api/users/{id}", // DELETE user by ID
                    "/api/fees/add",
                    "/api/permissions/add",
                    "/api/roles/add"
                ).hasRole("ADMIN") // ADMIN role can access

                // All other requests require authentication
                .anyRequest().authenticated()
            )

            // Enable HTTP Basic Authentication
            .httpBasic(Customizer.withDefaults())

            // Configure logout functionality
            .logout(logout -> logout
                .logoutUrl("/api/users/logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt for secure password encoding
    }
}

*/