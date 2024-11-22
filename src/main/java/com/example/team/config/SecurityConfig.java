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











/*package com.example.team.config;          //data 2024-11-22

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

   private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public SecurityConfig(CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }   

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for API
            .csrf(csrf -> csrf.disable())

            // Use stateless session
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

             // Configure custom AuthenticationEntryPoint
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(customAuthenticationEntryPoint)
            )  

            // Define authorization rules
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers(
                    "/api/users/register",
                    "/api/users/login",
                    "/api/users/logout", // For testing purposes
                    "/api/users/reset-password-request",
                    "/api/users/reset-password",
                    "/api/users/verify-otp",
                    "/api/courses", // GET all courses
                    "/api/courses/{id}", // GET course by ID
                    "/api/courses/{id}/enroll",
                    "/api/durations", // GET all durations
                    "/api/durations/type", // GET by type
                    "/api/durations/total-hours", // GET by total hours
                    "/api/durations/description", // GET by description keyword
                    "/api/fees", // GET all fees
                    "/api/permissions", // GET all permissions
                    "/api/roles", // GET all roles
                    "/api/schedules", // GET all schedules
                    "/api/schedules/add" // Add schedules publicly (optional)
                ).permitAll()

                // Authenticated endpoints
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
                ).authenticated()

                // Default: Require authentication for all other requests
                .anyRequest().authenticated()
            )

            // Enable HTTP Basic Authentication
            .httpBasic(httpBasic -> httpBasic.disable()) // Change as needed

            // Configure logout functionality
            .logout(logout -> logout
                .logoutUrl("/api/logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for secure password encoding
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
                // Public endpoints
                .requestMatchers(
                    "/api/users/register",
                    "/api/users/login",
                    "/api/users/logout",//<---   i just added it for testing 
                    "/api/users/reset-password-request",
                    "/api/users/reset-password",
                    "/api/users/verify-otp"
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