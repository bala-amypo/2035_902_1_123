// // // // package com.example.demo.config;

// // // // import com.example.demo.security.JwtAuthenticationFilter;
// // // // import org.springframework.context.annotation.Bean;
// // // // import org.springframework.context.annotation.Configuration;
// // // // import org.springframework.security.authentication.AuthenticationManager;
// // // // import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// // // // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // // // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // // // import org.springframework.security.config.http.SessionCreationPolicy;
// // // // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // // // import org.springframework.security.crypto.password.PasswordEncoder;
// // // // import org.springframework.security.web.SecurityFilterChain;
// // // // import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// // // // @Configuration
// // // // @EnableWebSecurity
// // // // public class SecurityConfig {

// // // //     private final JwtAuthenticationFilter jwtAuthenticationFilter;

// // // //     public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
// // // //         this.jwtAuthenticationFilter = jwtAuthenticationFilter;
// // // //     }

// // // //     @Bean
// // // //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// // // //         http
// // // //             .csrf(csrf -> csrf.disable())
// // // //             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// // // //             .authorizeHttpRequests(auth -> auth
// // // //                 .requestMatchers("/api/auth/**").permitAll()
// // // //                 .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
// // // //                 .anyRequest().authenticated()
// // // //             );

// // // //         http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
// // // //         return http.build();
// // // //     }

// // // //     // This @Bean annotation is what resolves your current error
// // // //     @Bean
// // // //     public PasswordEncoder passwordEncoder() {
// // // //         return new BCryptPasswordEncoder();
// // // //     }

// // // //     @Bean
// // // //     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
// // // //         return config.getAuthenticationManager();
// // // //     }
// // // // }
// // // @Bean
// // // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// // //     http
// // //         .cors(cors -> cors.disable()) // Let ServletConfig handle CORS or configure here
// // //         .csrf(csrf -> csrf.disable())
// // //         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// // //         .authorizeHttpRequests(auth -> auth
// // //             // Ensure Swagger endpoints are explicitly permitted
// // //             .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
// // //             .requestMatchers("/api/auth/**").permitAll()
// // //             .anyRequest().authenticated()
// // //         );

// // //     http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    
// // //     return http.build();
// // // }

// // package com.example.demo.config;

// // import com.example.demo.security.JwtAuthenticationFilter;
// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.security.authentication.AuthenticationManager;
// // import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // import org.springframework.security.config.http.SessionCreationPolicy;
// // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.security.web.SecurityFilterChain;
// // import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// // @Configuration
// // @EnableWebSecurity
// // public class SecurityConfig {

// //     private final JwtAuthenticationFilter jwtAuthenticationFilter;

// //     public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
// //         this.jwtAuthenticationFilter = jwtAuthenticationFilter;
// //     }

// //     @Bean
// //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// //         http
// //             .csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs
// //             .cors(cors -> cors.disable()) // Handled by ServletConfig
// //             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless JWT sessions
// //             .authorizeHttpRequests(auth -> auth
// //                 .requestMatchers("/api/auth/**").permitAll() // Allow registration/login
// //                 .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Allow Swagger
// //                 .anyRequest().authenticated() // Protect everything else
// //             );

// //         // Add our JWT filter before the standard auth filter
// //         http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
// //         return http.build();
// //     }

// //     @Bean
// //     public PasswordEncoder passwordEncoder() {
// //         return new BCryptPasswordEncoder(); // Matches requirement for password hashing
// //     }

// //     @Bean
// //     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
// //         return config.getAuthenticationManager();
// //     }
// // }

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//     http
//         // 1. Explicitly enable CORS and disable CSRF for REST APIs
//         .cors(cors -> cors.configure(http)) 
//         .csrf(csrf -> csrf.disable()) 
        
//         // 2. Set session to stateless
//         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        
//         // 3. Configure path permissions
//         .authorizeHttpRequests(auth -> auth
//             .requestMatchers("/api/auth/**").permitAll() // Must be public
//             .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
//             .anyRequest().authenticated()
//         );

//     http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    
//     return http.build();
// }

package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs
            .cors(cors -> {}) // Enable CORS support in Security
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless JWT sessions
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // Allow registration/login
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Allow Swagger access
                .anyRequest().authenticated() // Protect business logic
            );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password hashing
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}