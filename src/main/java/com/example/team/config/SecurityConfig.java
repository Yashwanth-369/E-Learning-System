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
                // Public endpoints
                .requestMatchers(
                    "/api/users/register",
                    "/api/users/login",
                    "/api/users/reset-password-request",
                    "/api/users/reset-password"
                ).permitAll() 
                
                // Course endpoints
                .requestMatchers(
                    "/api/courses", // GET all courses
                    "/api/courses/{id}" // GET course by ID
                ).permitAll()
                .requestMatchers(
                    "/api/courses/add",
                    "/api/courses/{id}/availability"
                ).authenticated()

                // Duration endpoints
                .requestMatchers(
                    "/api/durations", // GET all durations
                    "/api/durations/type", // GET by type
                    "/api/durations/total-hours", // GET by total hours
                    "/api/durations/description" // GET by description keyword
                ).permitAll()
                .requestMatchers(
                    "/api/durations/add",
                    "/api/durations/{id}",
                    "/api/durations/{id}/availability",
                    "/api/durations/{id}/delete" // Allow DELETE on durations with authentication
                ).authenticated()

                // User endpoints, including DELETE for user by ID
                .requestMatchers(
                    "/api/users/{id}" // DELETE user by ID, requires authentication
                ).authenticated()

                // Fees endpoints
                .requestMatchers(
                    "/api/fees" // GET all fees
                ).permitAll()
                .requestMatchers(
                    "/api/fees/add"
                ).authenticated()

                // Permissions endpoints
                .requestMatchers(
                    "/api/permissions" // GET all permissions
                ).permitAll()
                .requestMatchers(
                    "/api/permissions/add"
                ).authenticated()

                // Roles endpoints
                .requestMatchers(
                    "/api/roles" // GET all roles
                ).permitAll()
                .requestMatchers(
                    "/api/roles/add"
                ).authenticated()

                // Schedule endpoints
                .requestMatchers(
                    "/api/schedules", // GET all schedules
                    "/api/schedules/add"
                ).permitAll()

                // Default to requiring authentication for all other requests
                .anyRequest().authenticated()
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







/* package com.example.team.config;

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
                // Public endpoints
                .requestMatchers(
                    "/api/users/register",
                    "/api/users/login",
                    "/api/users/reset-password-request",
                    "/api/users/reset-password"
                ).permitAll() 
                
                // Course endpoints
                .requestMatchers(
                    "/api/courses", // GET all courses
                    "/api/courses/{id}" // GET course by ID
                ).permitAll()
                .requestMatchers(
                    "/api/courses/add",
                    "/api/courses/{id}/availability"
                ).authenticated()

                // Duration endpoints
                .requestMatchers(
                    "/api/durations", // GET all durations
                    "/api/durations/type", // GET by type
                    "/api/durations/total-hours", // GET by total hours
                    "/api/durations/description" // GET by description keyword
                ).permitAll()
                .requestMatchers(
                    "/api/durations/add",
                    "/api/durations/{id}",
                    "/api/durations/{id}/availability",
                    "/api/durations/{id}/delete"
                ).authenticated()

                // Fees endpoints
                .requestMatchers(
                    "/api/fees" // GET all fees
                ).permitAll()
                .requestMatchers(
                    "/api/fees/add"
                ).authenticated()

                // Permissions endpoints
                .requestMatchers(
                    "/api/permissions" // GET all permissions
                ).permitAll()
                .requestMatchers(
                    "/api/permissions/add"
                ).authenticated()

                // Roles endpoints
                .requestMatchers(
                    "/api/roles" // GET all roles
                ).permitAll()
                .requestMatchers(
                    "/api/roles/add"
                ).authenticated()

                // Schedule endpoints
                .requestMatchers(
                    "/api/schedules", // GET all schedules
                    "/api/schedules/add"
                ).permitAll()

                // Default to requiring authentication for all other requests
                .anyRequest().authenticated()
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